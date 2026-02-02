package com.lumber.inventory.ui.screens.reekon;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.view.HapticFeedbackConstants;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.CardDefaults;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.material3.SnackbarHostState;
import androidx.compose.runtime.Composable;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import com.lumber.inventory.data.ble.BleConnectionState;
import com.lumber.inventory.data.ble.MeasurementSlot;
import com.lumber.inventory.data.ble.ReekonDevice;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a\u0016\u0010\u0002\u001a\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0003\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\u001e\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0003\u001a8\u0010\f\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0011H\u0003\u001a\b\u0010\u0012\u001a\u00020\u0001H\u0003\u001al\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\n2\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u00112\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0003\u001aD\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u00072\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0003\u001a\u0016\u0010$\u001a\u00020\u00012\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0003\u001am\u0010&\u001a\u00020\u00012\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042K\u0010(\u001aG\u0012\u0013\u0012\u00110*\u00a2\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0013\u0012\u00110*\u00a2\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(.\u0012\u0013\u0012\u00110*\u00a2\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(/\u0012\u0004\u0012\u00020\u00010)2\b\b\u0002\u00100\u001a\u000201H\u0007\u001a8\u00102\u001a\u00020\u00012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000e2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00010\u00042\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0011H\u0003\u00a8\u00064"}, d2 = {"BleNotSupportedContent", "", "BluetoothDisabledContent", "onEnableBluetooth", "Lkotlin/Function0;", "ConnectingContent", "deviceName", "", "DeviceItem", "device", "Lcom/lumber/inventory/data/ble/ReekonDevice;", "onClick", "DeviceScanContent", "devices", "", "onStartScan", "onConnectDevice", "Lkotlin/Function1;", "DisconnectingContent", "MeasurementContent", "measurementState", "Lcom/lumber/inventory/data/ble/MeasurementInputState;", "connectedDevice", "onSlotSelected", "Lcom/lumber/inventory/data/ble/MeasurementSlot;", "onClearSlot", "onReset", "onDisconnect", "onConfirm", "MeasurementSlotCard", "label", "value", "isActive", "", "isFilled", "onClear", "PermissionsContent", "onRequestPermissions", "ReekonMeasurementScreen", "onNavigateBack", "onMeasurementsComplete", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "length", "width", "thickness", "viewModel", "Lcom/lumber/inventory/ui/screens/reekon/ReekonMeasurementViewModel;", "ScanningContent", "onStopScan", "app_debug"})
public final class ReekonMeasurementScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void ReekonMeasurementScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super java.lang.Double, ? super java.lang.Double, ? super java.lang.Double, kotlin.Unit> onMeasurementsComplete, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.ui.screens.reekon.ReekonMeasurementViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BleNotSupportedContent() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PermissionsContent(kotlin.jvm.functions.Function0<kotlin.Unit> onRequestPermissions) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BluetoothDisabledContent(kotlin.jvm.functions.Function0<kotlin.Unit> onEnableBluetooth) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DeviceScanContent(java.util.List<com.lumber.inventory.data.ble.ReekonDevice> devices, kotlin.jvm.functions.Function0<kotlin.Unit> onStartScan, kotlin.jvm.functions.Function1<? super com.lumber.inventory.data.ble.ReekonDevice, kotlin.Unit> onConnectDevice) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ScanningContent(java.util.List<com.lumber.inventory.data.ble.ReekonDevice> devices, kotlin.jvm.functions.Function0<kotlin.Unit> onStopScan, kotlin.jvm.functions.Function1<? super com.lumber.inventory.data.ble.ReekonDevice, kotlin.Unit> onConnectDevice) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DeviceItem(com.lumber.inventory.data.ble.ReekonDevice device, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ConnectingContent(java.lang.String deviceName) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DisconnectingContent() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MeasurementContent(com.lumber.inventory.data.ble.MeasurementInputState measurementState, com.lumber.inventory.data.ble.ReekonDevice connectedDevice, kotlin.jvm.functions.Function1<? super com.lumber.inventory.data.ble.MeasurementSlot, kotlin.Unit> onSlotSelected, kotlin.jvm.functions.Function1<? super com.lumber.inventory.data.ble.MeasurementSlot, kotlin.Unit> onClearSlot, kotlin.jvm.functions.Function0<kotlin.Unit> onReset, kotlin.jvm.functions.Function0<kotlin.Unit> onDisconnect, kotlin.jvm.functions.Function0<kotlin.Unit> onConfirm) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MeasurementSlotCard(java.lang.String label, java.lang.String value, boolean isActive, boolean isFilled, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, kotlin.jvm.functions.Function0<kotlin.Unit> onClear) {
    }
}