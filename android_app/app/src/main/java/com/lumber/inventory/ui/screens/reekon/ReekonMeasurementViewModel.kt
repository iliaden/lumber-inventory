package com.lumber.inventory.ui.screens.reekon

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lumber.inventory.data.ble.BleConnectionState
import com.lumber.inventory.data.ble.MeasurementInputManager
import com.lumber.inventory.data.ble.MeasurementInputState
import com.lumber.inventory.data.ble.MeasurementSlot
import com.lumber.inventory.data.ble.ReekonBleManager
import com.lumber.inventory.data.ble.ReekonDevice
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReekonUiState(
    val bleConnectionState: BleConnectionState = BleConnectionState.DISCONNECTED,
    val discoveredDevices: List<ReekonDevice> = emptyList(),
    val connectedDevice: ReekonDevice? = null,
    val measurementState: MeasurementInputState = MeasurementInputState(),
    val errorMessage: String? = null,
    val permissionsGranted: Boolean = false,
    val bluetoothEnabled: Boolean = false
)

@HiltViewModel
class ReekonMeasurementViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val bleManager: ReekonBleManager,
    private val measurementInputManager: MeasurementInputManager
) : ViewModel() {

    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _permissionsGranted = MutableStateFlow(false)

    val uiState: StateFlow<ReekonUiState> = combine(
        bleManager.connectionState,
        bleManager.discoveredDevices,
        bleManager.connectedDevice,
        measurementInputManager.state,
        _errorMessage,
        _permissionsGranted
    ) { values ->
        ReekonUiState(
            bleConnectionState = values[0] as BleConnectionState,
            discoveredDevices = @Suppress("UNCHECKED_CAST") (values[1] as List<ReekonDevice>),
            connectedDevice = values[2] as ReekonDevice?,
            measurementState = values[3] as MeasurementInputState,
            errorMessage = values[4] as String?,
            permissionsGranted = values[5] as Boolean,
            bluetoothEnabled = bleManager.isBluetoothEnabled()
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ReekonUiState()
    )

    init {
        checkPermissions()
        collectBleErrors()
        collectMeasurements()
    }

    private fun collectBleErrors() {
        viewModelScope.launch {
            bleManager.error.collect { error ->
                _errorMessage.value = error
            }
        }
    }

    private fun collectMeasurements() {
        viewModelScope.launch {
            bleManager.measurements.collect { measurement ->
                val accepted = measurementInputManager.processMeasurement(measurement)
                if (!accepted) {
                    // Measurement was a duplicate, could show a toast if needed
                }
            }
        }
    }

    fun checkPermissions() {
        val requiredPermissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            listOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            listOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }

        _permissionsGranted.value = requiredPermissions.all {
            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun getRequiredPermissions(): Array<String> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            )
        } else {
            arrayOf(
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }

    fun onPermissionsResult(granted: Boolean) {
        _permissionsGranted.value = granted
    }

    fun startScan() {
        if (!_permissionsGranted.value) {
            _errorMessage.value = "Bluetooth permissions required"
            return
        }
        bleManager.startScan()
    }

    fun stopScan() {
        bleManager.stopScan()
    }

    fun connectToDevice(device: ReekonDevice) {
        bleManager.connect(device)
    }

    fun disconnect() {
        bleManager.disconnect()
    }

    fun locateTool() {
        bleManager.locateTool()
    }

    fun setCurrentSlot(slot: MeasurementSlot) {
        measurementInputManager.setCurrentSlot(slot)
    }

    fun clearSlot(slot: MeasurementSlot) {
        measurementInputManager.clearSlot(slot)
    }

    fun resetMeasurements() {
        measurementInputManager.reset()
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun isBleSupported(): Boolean = bleManager.isBleSupported()

    override fun onCleared() {
        super.onCleared()
        bleManager.disconnect()
    }
}
