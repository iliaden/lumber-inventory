package com.lumber.inventory.ui.screens.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.model.Tag
import com.lumber.inventory.data.repository.LumberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class TagsUiState {
    object Loading : TagsUiState()
    data class Success(val tags: List<Tag>) : TagsUiState()
    data class Error(val message: String) : TagsUiState()
}

@HiltViewModel
class TagsViewModel @Inject constructor(
    private val repository: LumberRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<TagsUiState>(TagsUiState.Loading)
    val uiState: StateFlow<TagsUiState> = _uiState.asStateFlow()
    
    init {
        loadTags()
    }
    
    fun loadTags() {
        viewModelScope.launch {
            _uiState.value = TagsUiState.Loading
            
            repository.getTags()
                .onSuccess { tags ->
                    _uiState.value = TagsUiState.Success(tags)
                }
                .onError { message ->
                    _uiState.value = TagsUiState.Error(message)
                }
        }
    }
    
    fun createTag(name: String) {
        viewModelScope.launch {
            repository.createTag(name)
                .onSuccess {
                    loadTags()
                }
                .onError { message ->
                    _uiState.value = TagsUiState.Error(message)
                }
        }
    }
    
    fun deleteTag(id: Int) {
        viewModelScope.launch {
            repository.deleteTag(id)
                .onSuccess {
                    loadTags()
                }
                .onError { message ->
                    _uiState.value = TagsUiState.Error(message)
                }
        }
    }
}
