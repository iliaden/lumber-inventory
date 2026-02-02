package com.lumber.inventory.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.model.CreateLumberRequest
import com.lumber.inventory.data.model.Location
import com.lumber.inventory.data.model.Tag
import com.lumber.inventory.data.repository.LumberRepository
import com.lumber.inventory.util.FractionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LumberFormState(
    val species: String = "",
    val length: String = "",
    val width: String = "",
    val thickness: String = "",
    val planed: Boolean = false,
    val locationName: String = "",
    val selectedLocationId: Int? = null,
    val selectedTagIds: Set<Int> = emptySet(),
    val customTags: Set<String> = emptySet(),
    val speciesError: String? = null,
    val lengthError: String? = null,
    val widthError: String? = null,
    val thicknessError: String? = null,
    val fromReekon: Boolean = false
)

data class DropdownData(
    val species: List<String> = emptyList(),
    val locations: List<Location> = emptyList(),
    val tags: List<Tag> = emptyList(),
    val isLoading: Boolean = true
)

sealed class AddLumberUiState {
    object Idle : AddLumberUiState()
    object Loading : AddLumberUiState()
    data class Success(val clearMeasurementsOnly: Boolean = false) : AddLumberUiState()
    data class Error(val message: String) : AddLumberUiState()
}

@HiltViewModel
class AddLumberViewModel @Inject constructor(
    private val repository: LumberRepository
) : ViewModel() {

    private val _formState = MutableStateFlow(LumberFormState())
    val formState: StateFlow<LumberFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<AddLumberUiState>(AddLumberUiState.Idle)
    val uiState: StateFlow<AddLumberUiState> = _uiState.asStateFlow()

    private val _dropdownData = MutableStateFlow(DropdownData())
    val dropdownData: StateFlow<DropdownData> = _dropdownData.asStateFlow()

    init {
        loadDropdownData()
    }

    private fun loadDropdownData() {
        viewModelScope.launch {
            _dropdownData.update { it.copy(isLoading = true) }

            val speciesResult = repository.getSpecies()
            val locationsResult = repository.getLocations()
            val tagsResult = repository.getTags()

            _dropdownData.update { current ->
                current.copy(
                    species = when (speciesResult) {
                        is com.lumber.inventory.data.api.ApiResult.Success -> speciesResult.data
                        else -> emptyList()
                    },
                    locations = when (locationsResult) {
                        is com.lumber.inventory.data.api.ApiResult.Success -> locationsResult.data
                        else -> emptyList()
                    },
                    tags = when (tagsResult) {
                        is com.lumber.inventory.data.api.ApiResult.Success -> tagsResult.data
                        else -> emptyList()
                    },
                    isLoading = false
                )
            }
        }
    }

    fun refreshDropdownData() {
        loadDropdownData()
    }

    /**
     * Set initial measurements from Reekon device.
     * Converts inches to fractional display strings.
     */
    fun setInitialMeasurements(lengthInches: Double, widthInches: Double, thicknessInches: Double) {
        _formState.update { it.copy(
            length = FractionUtils.toFractionDisplay(lengthInches),
            width = FractionUtils.toFractionDisplay(widthInches),
            thickness = FractionUtils.toFractionDisplay(thicknessInches),
            fromReekon = true
        ) }
    }

    fun updateSpecies(value: String) {
        _formState.update { it.copy(species = value, speciesError = null) }
    }

    fun updateLength(value: String) {
        _formState.update { it.copy(length = value, lengthError = null) }
    }

    fun updateWidth(value: String) {
        _formState.update { it.copy(width = value, widthError = null) }
    }

    fun updateThickness(value: String) {
        _formState.update { it.copy(thickness = value, thicknessError = null) }
    }

    fun updatePlaned(value: Boolean) {
        _formState.update { it.copy(planed = value) }
    }

    fun updateLocationName(value: String) {
        _formState.update { it.copy(locationName = value, selectedLocationId = null) }
    }

    fun selectLocation(location: Location?) {
        _formState.update {
            it.copy(
                locationName = location?.name ?: "",
                selectedLocationId = location?.id
            )
        }
    }

    fun toggleTagSelection(tag: Tag) {
        _formState.update { current ->
            val newSelection = if (current.selectedTagIds.contains(tag.id)) {
                current.selectedTagIds - tag.id
            } else {
                current.selectedTagIds + tag.id
            }
            current.copy(selectedTagIds = newSelection)
        }
    }

    fun addCustomTag(tagName: String) {
        val trimmedName = tagName.trim()
        if (trimmedName.isNotBlank()) {
            _formState.update { current ->
                current.copy(customTags = current.customTags + trimmedName)
            }
        }
    }

    fun removeCustomTag(tagName: String) {
        _formState.update { current ->
            current.copy(customTags = current.customTags - tagName)
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        _formState.update { state ->
            state.copy(
                speciesError = if (state.species.isBlank()) {
                    isValid = false
                    "Species is required"
                } else null,
                lengthError = if (state.length.isBlank()) {
                    isValid = false
                    "Length is required"
                } else if (!FractionUtils.isValidFraction(state.length)) {
                    isValid = false
                    "Invalid dimension format"
                } else null,
                widthError = if (state.width.isBlank()) {
                    isValid = false
                    "Width is required"
                } else if (!FractionUtils.isValidFraction(state.width)) {
                    isValid = false
                    "Invalid dimension format"
                } else null,
                thicknessError = if (state.thickness.isBlank()) {
                    isValid = false
                    "Thickness is required"
                } else if (!FractionUtils.isValidFraction(state.thickness)) {
                    isValid = false
                    "Invalid dimension format"
                } else null
            )
        }

        return isValid
    }

    /**
     * Clear only the measurement fields (length, width, thickness) while preserving
     * species, location, tags, and planed status for bulk entry.
     */
    private fun clearMeasurementsOnly() {
        _formState.update { current ->
            current.copy(
                length = "",
                width = "",
                thickness = "",
                lengthError = null,
                widthError = null,
                thicknessError = null,
                fromReekon = false
            )
        }
    }

    /**
     * Reset the UI state back to Idle so user can continue adding more items.
     */
    fun resetUiState() {
        _uiState.value = AddLumberUiState.Idle
    }

    fun saveLumber() {
        if (!validateForm()) return

        viewModelScope.launch {
            _uiState.value = AddLumberUiState.Loading

            val form = _formState.value

            // Collect tags: selected tag IDs + custom tag names
            val selectedTagIds = form.selectedTagIds.toList().takeIf { it.isNotEmpty() }
            val customTagNames = form.customTags.toList().takeIf { it.isNotEmpty() }

            val request = CreateLumberRequest(
                species = form.species.trim(),
                length = form.length.trim(),
                width = form.width.trim(),
                thickness = form.thickness.trim(),
                planed = form.planed,
                locationId = form.selectedLocationId,
                locationName = if (form.selectedLocationId == null && form.locationName.isNotBlank()) {
                    form.locationName.trim()
                } else null,
                tagIds = selectedTagIds,
                tags = customTagNames
            )

            repository.createLumber(request)
                .onSuccess {
                    // Clear only measurements for bulk entry workflow
                    clearMeasurementsOnly()
                    // Refresh dropdown data to include any new species/tags/locations
                    refreshDropdownData()
                    _uiState.value = AddLumberUiState.Success(clearMeasurementsOnly = true)
                }
                .onError { message ->
                    _uiState.value = AddLumberUiState.Error(message)
                }
        }
    }
}
