package com.lumber.inventory.ui.screens.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.model.Location
import com.lumber.inventory.data.repository.LumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class LocationsUiState {
    object Loading : LocationsUiState()
    data class Success(val locations: List<Location>) : LocationsUiState()
    data class Error(val message: String) : LocationsUiState()
}

@HiltViewModel
class LocationsViewModel @Inject constructor(
    private val repository: LumberRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<LocationsUiState>(LocationsUiState.Loading)
    val uiState: StateFlow<LocationsUiState> = _uiState.asStateFlow()
    
    init {
        loadLocations()
    }
    
    fun loadLocations() {
        viewModelScope.launch {
            _uiState.value = LocationsUiState.Loading
            
            repository.getLocations()
                .onSuccess { locations ->
                    _uiState.value = LocationsUiState.Success(locations)
                }
                .onError { message ->
                    _uiState.value = LocationsUiState.Error(message)
                }
        }
    }
    
    fun createLocation(name: String) {
        viewModelScope.launch {
            repository.createLocation(name)
                .onSuccess {
                    loadLocations()
                }
                .onError { message ->
                    _uiState.value = LocationsUiState.Error(message)
                }
        }
    }
    
    fun deleteLocation(id: Int) {
        viewModelScope.launch {
            repository.deleteLocation(id)
                .onSuccess {
                    loadLocations()
                }
                .onError { message ->
                    _uiState.value = LocationsUiState.Error(message)
                }
        }
    }
}
