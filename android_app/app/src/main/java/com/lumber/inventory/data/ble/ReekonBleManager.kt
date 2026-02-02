package com.lumber.inventory.data.ble

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.os.Build
import android.os.ParcelUuid
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manager for handling BLE communication with Reekon measurement tools.
 * Implements the REEKON BLE SDK protocol for T1/T1M devices.
 */
@Singleton
@SuppressLint("MissingPermission")
class ReekonBleManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val TAG = "ReekonBleManager"
        private const val SCAN_TIMEOUT_MS = 10000L
        private const val MTU_SIZE = 251
    }

    private val bluetoothManager: BluetoothManager? by lazy {
        context.getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
    }

    private val bluetoothAdapter: BluetoothAdapter? by lazy {
        bluetoothManager?.adapter
    }

    private val bleScanner: BluetoothLeScanner? by lazy {
        bluetoothAdapter?.bluetoothLeScanner
    }

    private var bluetoothGatt: BluetoothGatt? = null
    private var syncCommCharacteristic: BluetoothGattCharacteristic? = null

    private val _connectionState = MutableStateFlow(BleConnectionState.DISCONNECTED)
    val connectionState: StateFlow<BleConnectionState> = _connectionState.asStateFlow()

    private val _discoveredDevices = MutableStateFlow<List<ReekonDevice>>(emptyList())
    val discoveredDevices: StateFlow<List<ReekonDevice>> = _discoveredDevices.asStateFlow()

    private val _connectedDevice = MutableStateFlow<ReekonDevice?>(null)
    val connectedDevice: StateFlow<ReekonDevice?> = _connectedDevice.asStateFlow()

    private val _measurements = MutableSharedFlow<ReekonMeasurement>(extraBufferCapacity = 10)
    val measurements: SharedFlow<ReekonMeasurement> = _measurements.asSharedFlow()

    private val _error = MutableSharedFlow<String>(extraBufferCapacity = 5)
    val error: SharedFlow<String> = _error.asSharedFlow()

    /**
     * Check if Bluetooth is available and enabled.
     */
    fun isBluetoothEnabled(): Boolean {
        return bluetoothAdapter?.isEnabled == true
    }

    /**
     * Check if BLE is supported on this device.
     */
    fun isBleSupported(): Boolean {
        return context.packageManager.hasSystemFeature(android.content.pm.PackageManager.FEATURE_BLUETOOTH_LE)
    }

    /**
     * Start scanning for Reekon devices.
     */
    fun startScan() {
        if (!isBluetoothEnabled()) {
            _error.tryEmit("Bluetooth is not enabled")
            return
        }

        if (_connectionState.value == BleConnectionState.SCANNING) {
            return
        }

        _discoveredDevices.value = emptyList()
        _connectionState.value = BleConnectionState.SCANNING

        val scanFilter = ScanFilter.Builder()
            .setServiceUuid(ParcelUuid(UUID.fromString(ReekonBleUuids.SYNC_SERVICE_UUID)))
            .build()

        val scanSettings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
            .build()

        try {
            bleScanner?.startScan(listOf(scanFilter), scanSettings, scanCallback)
            Log.d(TAG, "Started BLE scan for Reekon devices")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start scan", e)
            _error.tryEmit("Failed to start scan: ${e.message}")
            _connectionState.value = BleConnectionState.DISCONNECTED
        }
    }

    /**
     * Stop scanning for devices.
     */
    fun stopScan() {
        if (_connectionState.value == BleConnectionState.SCANNING) {
            try {
                bleScanner?.stopScan(scanCallback)
                Log.d(TAG, "Stopped BLE scan")
            } catch (e: Exception) {
                Log.e(TAG, "Failed to stop scan", e)
            }
            _connectionState.value = BleConnectionState.DISCONNECTED
        }
    }

    /**
     * Connect to a Reekon device.
     */
    fun connect(device: ReekonDevice) {
        if (_connectionState.value == BleConnectionState.CONNECTED ||
            _connectionState.value == BleConnectionState.CONNECTING) {
            return
        }

        stopScan()
        _connectionState.value = BleConnectionState.CONNECTING

        val bluetoothDevice = bluetoothAdapter?.getRemoteDevice(device.address)
        if (bluetoothDevice == null) {
            _error.tryEmit("Could not find device")
            _connectionState.value = BleConnectionState.DISCONNECTED
            return
        }

        bluetoothGatt = bluetoothDevice.connectGatt(context, false, gattCallback, BluetoothDevice.TRANSPORT_LE)
        _connectedDevice.value = device
        Log.d(TAG, "Connecting to ${device.name}")
    }

    /**
     * Disconnect from the current device.
     */
    fun disconnect() {
        _connectionState.value = BleConnectionState.DISCONNECTING
        bluetoothGatt?.let { gatt ->
            gatt.disconnect()
            gatt.close()
        }
        bluetoothGatt = null
        syncCommCharacteristic = null
        _connectedDevice.value = null
        _connectionState.value = BleConnectionState.DISCONNECTED
        Log.d(TAG, "Disconnected")
    }

    /**
     * Send an opcode to the device.
     */
    fun sendOpcode(opcode: Int, data: ByteArray = byteArrayOf()) {
        val characteristic = syncCommCharacteristic
        if (characteristic == null || bluetoothGatt == null) {
            _error.tryEmit("Not connected to device")
            return
        }

        val buffer = ByteBuffer.allocate(8 + data.size)
            .order(ByteOrder.LITTLE_ENDIAN)
            .putInt(opcode)
            .putInt(data.size)
            .put(data)

        characteristic.value = buffer.array()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bluetoothGatt?.writeCharacteristic(
                characteristic,
                buffer.array(),
                BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT
            )
        } else {
            @Suppress("DEPRECATION")
            bluetoothGatt?.writeCharacteristic(characteristic)
        }

        Log.d(TAG, "Sent opcode $opcode with ${data.size} bytes")
    }

    /**
     * Request locate tool (makes the device beep/flash).
     */
    fun locateTool() {
        sendOpcode(ReekonOpcodes.LOCATE_TOOL)
    }

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            parseReekonDevice(result)?.let { device ->
                val currentList = _discoveredDevices.value.toMutableList()
                val existingIndex = currentList.indexOfFirst { it.address == device.address }

                if (existingIndex >= 0) {
                    currentList[existingIndex] = device
                } else {
                    currentList.add(device)
                }

                _discoveredDevices.value = currentList
                Log.d(TAG, "Found device: ${device.name} (${device.deviceType.displayName})")
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.e(TAG, "Scan failed with error code: $errorCode")
            _error.tryEmit("Scan failed: error code $errorCode")
            _connectionState.value = BleConnectionState.DISCONNECTED
        }
    }

    private fun parseReekonDevice(result: ScanResult): ReekonDevice? {
        val scanRecord = result.scanRecord ?: return null
        val device = result.device

        // Try to get manufacturer data
        val manufacturerData = scanRecord.getManufacturerSpecificData(ReekonBleUuids.REEKON_COMPANY_ID)

        if (manufacturerData != null && manufacturerData.size >= 9) {
            val deviceType = ReekonDeviceType.fromCode(manufacturerData[0].toInt() and 0xFF)
            val deviceId = ByteBuffer.wrap(manufacturerData, 1, 8)
                .order(ByteOrder.LITTLE_ENDIAN)
                .long

            return ReekonDevice(
                name = device.name ?: "Reekon Device",
                address = device.address,
                deviceType = deviceType,
                deviceId = deviceId,
                rssi = result.rssi
            )
        }

        // Fallback: if we found the service UUID, create device with unknown type
        if (scanRecord.serviceUuids?.any {
            it.uuid.toString().equals(ReekonBleUuids.SYNC_SERVICE_UUID, ignoreCase = true)
        } == true) {
            return ReekonDevice(
                name = device.name ?: "Reekon Device",
                address = device.address,
                deviceType = ReekonDeviceType.NULL,
                deviceId = 0,
                rssi = result.rssi
            )
        }

        return null
    }

    private val gattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    Log.d(TAG, "Connected to GATT server")
                    gatt.requestMtu(MTU_SIZE)
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    Log.d(TAG, "Disconnected from GATT server")
                    _connectionState.value = BleConnectionState.DISCONNECTED
                    _connectedDevice.value = null
                    syncCommCharacteristic = null
                }
            }
        }

        override fun onMtuChanged(gatt: BluetoothGatt, mtu: Int, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "MTU changed to $mtu")
                gatt.discoverServices()
            } else {
                Log.e(TAG, "MTU change failed with status $status")
                gatt.discoverServices()
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "Services discovered")
                setupNotifications(gatt)
            } else {
                Log.e(TAG, "Service discovery failed with status $status")
                _error.tryEmit("Failed to discover services")
                disconnect()
            }
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray
        ) {
            handleNotification(value)
        }

        @Deprecated("Deprecated in API 33")
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic
        ) {
            @Suppress("DEPRECATION")
            handleNotification(characteristic.value)
        }

        override fun onDescriptorWrite(
            gatt: BluetoothGatt,
            descriptor: BluetoothGattDescriptor,
            status: Int
        ) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "Notifications enabled")
                _connectionState.value = BleConnectionState.CONNECTED
            } else {
                Log.e(TAG, "Failed to enable notifications")
                _error.tryEmit("Failed to enable notifications")
            }
        }
    }

    private fun setupNotifications(gatt: BluetoothGatt) {
        val syncService = gatt.getService(UUID.fromString(ReekonBleUuids.SYNC_SERVICE_UUID))
        if (syncService == null) {
            Log.e(TAG, "Sync service not found")
            _error.tryEmit("Reekon sync service not found")
            disconnect()
            return
        }

        val commCharacteristic = syncService.getCharacteristic(
            UUID.fromString(ReekonBleUuids.SYNC_COMM_CHARACTERISTIC_UUID)
        )
        if (commCharacteristic == null) {
            Log.e(TAG, "Comm characteristic not found")
            _error.tryEmit("Comm characteristic not found")
            disconnect()
            return
        }

        syncCommCharacteristic = commCharacteristic

        // Enable notifications
        gatt.setCharacteristicNotification(commCharacteristic, true)

        val descriptor = commCharacteristic.getDescriptor(
            UUID.fromString(ReekonBleUuids.CCCD_UUID)
        )
        if (descriptor != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                gatt.writeDescriptor(descriptor, BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE)
            } else {
                @Suppress("DEPRECATION")
                descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                @Suppress("DEPRECATION")
                gatt.writeDescriptor(descriptor)
            }
        } else {
            Log.e(TAG, "CCCD not found")
            _connectionState.value = BleConnectionState.CONNECTED
        }
    }

    private fun handleNotification(data: ByteArray) {
        if (data.size < 8) {
            Log.w(TAG, "Notification too short: ${data.size} bytes")
            return
        }

        val buffer = ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN)
        val opcode = buffer.int
        val dataLen = buffer.int

        Log.d(TAG, "Received opcode $opcode with $dataLen bytes")

        when (opcode) {
            ReekonOpcodes.MEASUREMENT -> {
                if (dataLen >= 20) {
                    val measurement = parseMeasurement(buffer, dataLen)
                    _measurements.tryEmit(measurement)
                    Log.d(TAG, "Measurement: ${measurement.positionInches}\" (${measurement.positionUm} μm)")
                }
            }
            ReekonOpcodes.RESPOND_UNSYNCED_MEASUREMENTS,
            ReekonOpcodes.RESPOND_ALL_MEASUREMENTS,
            ReekonOpcodes.RESPOND_MEASUREMENTS_FROM_GROUP -> {
                parseMeasurementArray(buffer, dataLen)
            }
        }
    }

    private fun parseMeasurement(buffer: ByteBuffer, dataLen: Int): ReekonMeasurement {
        val uuid = buffer.int.toLong() and 0xFFFFFFFFL
        val flags = buffer.int.toLong() and 0xFFFFFFFFL
        val positionUm = buffer.int
        val zeroDeltaUm = buffer.int.toLong() and 0xFFFFFFFFL
        val activeOffsetUm = buffer.int

        val groupNumber = if (dataLen >= 22) buffer.short.toInt() and 0xFFFF else null
        val measurementNumber = if (dataLen >= 24) buffer.short.toInt() and 0xFFFF else null

        return ReekonMeasurement(
            uuid = uuid,
            flags = flags,
            positionUm = positionUm,
            zeroDeltaUm = zeroDeltaUm,
            activeOffsetUm = activeOffsetUm,
            groupNumber = groupNumber,
            measurementNumber = measurementNumber
        )
    }

    private fun parseMeasurementArray(buffer: ByteBuffer, totalDataLen: Int) {
        var bytesRead = 0
        val measurementSize = if (totalDataLen >= 24) 24 else 20

        while (bytesRead + measurementSize <= totalDataLen && buffer.remaining() >= measurementSize) {
            val measurement = parseMeasurement(buffer, measurementSize)
            _measurements.tryEmit(measurement)
            bytesRead += measurementSize
        }
    }
}
