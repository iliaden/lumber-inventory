package com.lumber.inventory.ui.screens.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.repository.LumberRepository
import com.lumber.inventory.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SetupUiState(
    val serverUrl: String = SettingsRepository.DEFAULT_SERVER_URL,
    val isLoading: Boolean = false,
    val connectionSuccess: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val lumberRepository: LumberRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SetupUiState())
    val uiState: StateFlow<SetupUiState> = _uiState.asStateFlow()

    init {
        loadInitialUrl()
    }

    private fun loadInitialUrl() {
        viewModelScope.launch {
            val savedUrl = settingsRepository.serverUrlFlow.first()
            _uiState.value = _uiState.value.copy(serverUrl = savedUrl)
        }
    }

    fun updateServerUrl(url: String) {
        _uiState.value = _uiState.value.copy(
            serverUrl = url,
            errorMessage = null,
            connectionSuccess = false
        )
    }

    fun testConnection(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            val url = _uiState.value.serverUrl.trimEnd('/')
            settingsRepository.setServerUrl(url)

            try {
                val result = lumberRepository.healthCheck()
                when (result) {
                    is com.lumber.inventory.data.api.ApiResult.Success -> {
                        val health = result.data
                        if (health.status == "healthy") {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                connectionSuccess = true
                            )
                            settingsRepository.setSetupCompleted(true)
                            onSuccess()
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                errorMessage = "Server returned unhealthy status"
                            )
                        }
                    }
                    is com.lumber.inventory.data.api.ApiResult.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = "Connection failed: ${result.message}"
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Connection failed: ${e.message ?: "Unknown error"}"
                )
            }
        }
    }

    fun skipSetup(onComplete: () -> Unit) {
        viewModelScope.launch {
            val url = _uiState.value.serverUrl.trimEnd('/')
            settingsRepository.setServerUrl(url)
            settingsRepository.setSetupCompleted(true)
            onComplete()
        }
    }
}
