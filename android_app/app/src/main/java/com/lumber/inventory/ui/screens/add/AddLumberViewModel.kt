package com.lumber.inventory.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.model.CreateLumberRequest
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
    val tags: String = "",
    val speciesError: String? = null,
    val lengthError: String? = null,
    val widthError: String? = null,
    val thicknessError: String? = null
)

sealed class AddLumberUiState {
    object Idle : AddLumberUiState()
    object Loading : AddLumberUiState()
    object Success : AddLumberUiState()
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
        _formState.update { it.copy(locationName = value) }
    }

    fun updateTags(value: String) {
        _formState.update { it.copy(tags = value) }
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

    fun saveLumber() {
        if (!validateForm()) return

        viewModelScope.launch {
            _uiState.value = AddLumberUiState.Loading

            val form = _formState.value
            val tags = form.tags.split(",")
                .map { it.trim() }
                .filter { it.isNotBlank() }

            val request = CreateLumberRequest(
                species = form.species.trim(),
                length = form.length.trim(),
                width = form.width.trim(),
                thickness = form.thickness.trim(),
                planed = form.planed,
                locationName = form.locationName.trim().takeIf { it.isNotBlank() },
                tags = tags.takeIf { it.isNotEmpty() }
            )

            repository.createLumber(request)
                .onSuccess {
                    _uiState.value = AddLumberUiState.Success
                }
                .onError { message ->
                    _uiState.value = AddLumberUiState.Error(message)
                }
        }
    }
}
