package com.lumber.inventory.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.repository.LumberRepository
import com.lumber.inventory.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class ConnectionState {
    object Idle : ConnectionState()
    object Testing : ConnectionState()
    object Success : ConnectionState()
    data class Error(val message: String) : ConnectionState()
}

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val lumberRepository: LumberRepository
) : ViewModel() {

    val serverUrl: StateFlow<String> = settingsRepository.serverUrlFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SettingsRepository.DEFAULT_SERVER_URL
        )

    private val _connectionState = MutableStateFlow<ConnectionState>(ConnectionState.Idle)
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    fun saveServerUrl(url: String) {
        viewModelScope.launch {
            settingsRepository.setServerUrl(url)
        }
    }

    fun testConnection(url: String) {
        viewModelScope.launch {
            _connectionState.value = ConnectionState.Testing

            // Temporarily save the URL to test with
            settingsRepository.setServerUrl(url)

            lumberRepository.healthCheck()
                .onSuccess {
                    _connectionState.value = ConnectionState.Success
                }
                .onError { message ->
                    _connectionState.value = ConnectionState.Error(message)
                }
        }
    }
}
