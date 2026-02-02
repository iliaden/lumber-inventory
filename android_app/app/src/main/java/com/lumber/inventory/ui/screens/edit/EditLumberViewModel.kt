package com.lumber.inventory.ui.screens.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.model.UpdateLumberRequest
import com.lumber.inventory.data.repository.LumberRepository
import com.lumber.inventory.ui.screens.add.LumberFormState
import com.lumber.inventory.util.FractionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class EditLumberUiState {
    object Loading : EditLumberUiState()
    object Loaded : EditLumberUiState()
    object Saving : EditLumberUiState()
    object Updated : EditLumberUiState()
    object Deleted : EditLumberUiState()
    data class Error(val message: String) : EditLumberUiState()
}

@HiltViewModel
class EditLumberViewModel @Inject constructor(
    private val repository: LumberRepository
) : ViewModel() {

    private val _formState = MutableStateFlow(LumberFormState())
    val formState: StateFlow<LumberFormState> = _formState.asStateFlow()

    private val _uiState = MutableStateFlow<EditLumberUiState>(EditLumberUiState.Loading)
    val uiState: StateFlow<EditLumberUiState> = _uiState.asStateFlow()

    private var lumberId: Int = -1

    fun loadLumber(id: Int) {
        lumberId = id
        viewModelScope.launch {
            _uiState.value = EditLumberUiState.Loading

            repository.getLumber(id)
                .onSuccess { lumber ->
                    _formState.value = LumberFormState(
                        species = lumber.species,
                        length = lumber.lengthDisplay ?: lumber.length.toString(),
                        width = lumber.widthDisplay ?: lumber.width.toString(),
                        thickness = lumber.thicknessDisplay ?: lumber.thickness.toString(),
                        planed = lumber.planed,
                        locationName = lumber.location ?: "",
                        tags = lumber.tags.joinToString(", ")
                    )
                    _uiState.value = EditLumberUiState.Loaded
                }
                .onError { message ->
                    _uiState.value = EditLumberUiState.Error(message)
                }
        }
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
            _uiState.value = EditLumberUiState.Saving

            val form = _formState.value
            val tags = form.tags.split(",")
                .map { it.trim() }
                .filter { it.isNotBlank() }

            val request = UpdateLumberRequest(
                species = form.species.trim(),
                length = form.length.trim(),
                width = form.width.trim(),
                thickness = form.thickness.trim(),
                planed = form.planed,
                locationName = form.locationName.trim().takeIf { it.isNotBlank() },
                tags = tags
            )

            repository.updateLumber(lumberId, request)
                .onSuccess {
                    _uiState.value = EditLumberUiState.Updated
                }
                .onError { message ->
                    _uiState.value = EditLumberUiState.Error(message)
                }
        }
    }

    fun deleteLumber() {
        viewModelScope.launch {
            _uiState.value = EditLumberUiState.Saving

            repository.deleteLumber(lumberId)
                .onSuccess {
                    _uiState.value = EditLumberUiState.Deleted
                }
                .onError { message ->
                    _uiState.value = EditLumberUiState.Error(message)
                }
        }
    }
}
