package com.lumber.inventory.ui.screens.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.model.Lumber
import com.lumber.inventory.data.model.LumberFilter
import com.lumber.inventory.data.repository.LumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class InventoryUiState {
    object Loading : InventoryUiState()
    data class Success(val lumber: List<Lumber>) : InventoryUiState()
    data class Error(val message: String) : InventoryUiState()
}

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val repository: LumberRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<InventoryUiState>(InventoryUiState.Loading)
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()
    
    private var currentFilter = LumberFilter.EMPTY
    
    init {
        loadLumber()
    }
    
    fun loadLumber(filter: LumberFilter = currentFilter) {
        currentFilter = filter
        viewModelScope.launch {
            _uiState.value = InventoryUiState.Loading
            
            repository.getLumberList(filter)
                .onSuccess { lumber ->
                    _uiState.value = InventoryUiState.Success(lumber)
                }
                .onError { message ->
                    _uiState.value = InventoryUiState.Error(message)
                }
        }
    }
    
    fun refresh() {
        loadLumber()
    }
    
    fun applyFilter(filter: LumberFilter) {
        loadLumber(filter)
    }
    
    fun clearFilter() {
        loadLumber(LumberFilter.EMPTY)
    }
}
