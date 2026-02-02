package com.lumber.inventory.ui.screens.reekon

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.BluetoothConnected
import androidx.compose.material.icons.filled.BluetoothSearching
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lumber.inventory.data.ble.BleConnectionState
import com.lumber.inventory.data.ble.MeasurementSlot
import com.lumber.inventory.data.ble.ReekonDevice

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReekonMeasurementScreen(
    onNavigateBack: () -> Unit,
    onMeasurementsComplete: (length: Double, width: Double, thickness: Double) -> Unit,
    viewModel: ReekonMeasurementViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        viewModel.onPermissionsResult(allGranted)
    }

    // Bluetooth enable launcher
    val bluetoothEnableLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        viewModel.checkPermissions()
    }

    // Show error messages
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { error ->
            snackbarHostState.showSnackbar(error)
            viewModel.clearError()
        }
    }

    // Check permissions on launch
    LaunchedEffect(Unit) {
        viewModel.checkPermissions()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Reekon Measurement") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                },
                actions = {
                    if (uiState.bleConnectionState == BleConnectionState.CONNECTED) {
                        IconButton(onClick = { viewModel.locateTool() }) {
                            Icon(Icons.Default.BluetoothSearching, "Locate Tool")
                        }
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Check BLE support
            if (!viewModel.isBleSupported()) {
                BleNotSupportedContent()
                return@Scaffold
            }

            // Check permissions
            if (!uiState.permissionsGranted) {
                PermissionsContent(
                    onRequestPermissions = {
                        permissionLauncher.launch(viewModel.getRequiredPermissions())
                    }
                )
                return@Scaffold
            }

            // Check Bluetooth enabled
            if (!uiState.bluetoothEnabled) {
                BluetoothDisabledContent(
                    onEnableBluetooth = {
                        bluetoothEnableLauncher.launch(
                            Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                        )
                    }
                )
                return@Scaffold
            }

            when (uiState.bleConnectionState) {
                BleConnectionState.DISCONNECTED -> {
                    DeviceScanContent(
                        devices = uiState.discoveredDevices,
                        onStartScan = { viewModel.startScan() },
                        onConnectDevice = { viewModel.connectToDevice(it) }
                    )
                }
                BleConnectionState.SCANNING -> {
                    ScanningContent(
                        devices = uiState.discoveredDevices,
                        onStopScan = { viewModel.stopScan() },
                        onConnectDevice = { viewModel.connectToDevice(it) }
                    )
                }
                BleConnectionState.CONNECTING -> {
                    ConnectingContent(deviceName = uiState.connectedDevice?.name ?: "")
                }
                BleConnectionState.CONNECTED -> {
                    MeasurementContent(
                        measurementState = uiState.measurementState,
                        connectedDevice = uiState.connectedDevice,
                        onSlotSelected = { viewModel.setCurrentSlot(it) },
                        onClearSlot = { viewModel.clearSlot(it) },
                        onReset = { viewModel.resetMeasurements() },
                        onDisconnect = { viewModel.disconnect() },
                        onConfirm = {
                            val state = uiState.measurementState
                            if (state.lengthInches != null &&
                                state.widthInches != null &&
                                state.thicknessInches != null) {
                                onMeasurementsComplete(
                                    state.lengthInches!!,
                                    state.widthInches!!,
                                    state.thicknessInches!!
                                )
                            }
                        }
                    )
                }
                BleConnectionState.DISCONNECTING -> {
                    DisconnectingContent()
                }
            }
        }
    }
}

@Composable
private fun BleNotSupportedContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Bluetooth,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bluetooth LE Not Supported",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "This device does not support Bluetooth Low Energy",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun PermissionsContent(onRequestPermissions: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Bluetooth,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bluetooth Permissions Required",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Grant Bluetooth permissions to connect to your Reekon device",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRequestPermissions) {
            Text("Grant Permissions")
        }
    }
}

@Composable
private fun BluetoothDisabledContent(onEnableBluetooth: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Bluetooth,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bluetooth is Disabled",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Enable Bluetooth to connect to your Reekon device",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onEnableBluetooth) {
            Text("Enable Bluetooth")
        }
    }
}

@Composable
private fun DeviceScanContent(
    devices: List<ReekonDevice>,
    onStartScan: () -> Unit,
    onConnectDevice: (ReekonDevice) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.BluetoothSearching,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Scan for Reekon Devices",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Make sure your T1M is turned on and nearby",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onStartScan,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Bluetooth, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Start Scan")
                }
            }
        }

        if (devices.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Previously Found Devices",
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                items(devices) { device ->
                    DeviceItem(
                        device = device,
                        onClick = { onConnectDevice(device) }
                    )
                }
            }
        }
    }
}

@Composable
private fun ScanningContent(
    devices: List<ReekonDevice>,
    onStopScan: () -> Unit,
    onConnectDevice: (ReekonDevice) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Scanning for devices...",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${devices.size} device(s) found",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                OutlinedButton(onClick = onStopScan) {
                    Text("Stop")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (devices.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Searching...\nMake sure your Reekon device is on",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn {
                items(devices) { device ->
                    DeviceItem(
                        device = device,
                        onClick = { onConnectDevice(device) }
                    )
                }
            }
        }
    }
}

@Composable
private fun DeviceItem(
    device: ReekonDevice,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Straighten,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = device.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = device.deviceType.displayName,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "${device.rssi} dBm",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ConnectingContent(deviceName: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Connecting to $deviceName...",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun DisconnectingContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Disconnecting...",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun MeasurementContent(
    measurementState: com.lumber.inventory.data.ble.MeasurementInputState,
    connectedDevice: ReekonDevice?,
    onSlotSelected: (MeasurementSlot) -> Unit,
    onClearSlot: (MeasurementSlot) -> Unit,
    onReset: () -> Unit,
    onDisconnect: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Connected device info
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.BluetoothConnected,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = connectedDevice?.name ?: "Connected",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = connectedDevice?.deviceType?.displayName ?: "",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                OutlinedButton(onClick = onDisconnect) {
                    Text("Disconnect")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Instructions
        Text(
            text = "Take measurements with your Reekon tool",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Tap a slot to select it, then measure",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Measurement slots
        MeasurementSlotCard(
            label = "Length",
            value = measurementState.lengthDisplay,
            isActive = measurementState.currentSlot == MeasurementSlot.LENGTH,
            isFilled = measurementState.length != null,
            onClick = { onSlotSelected(MeasurementSlot.LENGTH) },
            onClear = { onClearSlot(MeasurementSlot.LENGTH) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        MeasurementSlotCard(
            label = "Width",
            value = measurementState.widthDisplay,
            isActive = measurementState.currentSlot == MeasurementSlot.WIDTH,
            isFilled = measurementState.width != null,
            onClick = { onSlotSelected(MeasurementSlot.WIDTH) },
            onClear = { onClearSlot(MeasurementSlot.WIDTH) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        MeasurementSlotCard(
            label = "Thickness",
            value = measurementState.thicknessDisplay,
            isActive = measurementState.currentSlot == MeasurementSlot.THICKNESS,
            isFilled = measurementState.thickness != null,
            onClick = { onSlotSelected(MeasurementSlot.THICKNESS) },
            onClear = { onClearSlot(MeasurementSlot.THICKNESS) }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onReset,
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Default.Refresh, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reset")
            }

            Button(
                onClick = onConfirm,
                modifier = Modifier.weight(1f),
                enabled = measurementState.length != null &&
                        measurementState.width != null &&
                        measurementState.thickness != null
            ) {
                Icon(Icons.Default.Check, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Confirm")
            }
        }
    }
}

@Composable
private fun MeasurementSlotCard(
    label: String,
    value: String,
    isActive: Boolean,
    isFilled: Boolean,
    onClick: () -> Unit,
    onClear: () -> Unit
) {
    val borderColor = when {
        isActive -> MaterialTheme.colorScheme.primary
        isFilled -> MaterialTheme.colorScheme.outline
        else -> Color.Transparent
    }

    val backgroundColor = when {
        isActive -> MaterialTheme.colorScheme.primaryContainer
        isFilled -> MaterialTheme.colorScheme.surfaceVariant
        else -> MaterialTheme.colorScheme.surface
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = if (isActive) 2.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Status indicator
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        if (isFilled) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (isFilled) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Straighten,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            if (isFilled) {
                IconButton(onClick = onClear) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
