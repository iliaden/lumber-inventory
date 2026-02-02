package com.lumber.inventory.ui.screens.reekon;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import com.lumber.inventory.data.ble.BleConnectionState;
import com.lumber.inventory.data.ble.MeasurementInputManager;
import com.lumber.inventory.data.ble.MeasurementInputState;
import com.lumber.inventory.data.ble.MeasurementSlot;
import com.lumber.inventory.data.ble.ReekonBleManager;
import com.lumber.inventory.data.ble.ReekonDevice;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003J\t\u0010 \u001a\u00020\tH\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u0010\"\u001a\u00020\rH\u00c6\u0003J\t\u0010#\u001a\u00020\rH\u00c6\u0003JY\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rH\u00c6\u0001J\u0013\u0010%\u001a\u00020\r2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013\u00a8\u0006*"}, d2 = {"Lcom/lumber/inventory/ui/screens/reekon/ReekonUiState;", "", "bleConnectionState", "Lcom/lumber/inventory/data/ble/BleConnectionState;", "discoveredDevices", "", "Lcom/lumber/inventory/data/ble/ReekonDevice;", "connectedDevice", "measurementState", "Lcom/lumber/inventory/data/ble/MeasurementInputState;", "errorMessage", "", "permissionsGranted", "", "bluetoothEnabled", "(Lcom/lumber/inventory/data/ble/BleConnectionState;Ljava/util/List;Lcom/lumber/inventory/data/ble/ReekonDevice;Lcom/lumber/inventory/data/ble/MeasurementInputState;Ljava/lang/String;ZZ)V", "getBleConnectionState", "()Lcom/lumber/inventory/data/ble/BleConnectionState;", "getBluetoothEnabled", "()Z", "getConnectedDevice", "()Lcom/lumber/inventory/data/ble/ReekonDevice;", "getDiscoveredDevices", "()Ljava/util/List;", "getErrorMessage", "()Ljava/lang/String;", "getMeasurementState", "()Lcom/lumber/inventory/data/ble/MeasurementInputState;", "getPermissionsGranted", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class ReekonUiState {
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.ble.BleConnectionState bleConnectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.lumber.inventory.data.ble.ReekonDevice> discoveredDevices = null;
    @org.jetbrains.annotations.Nullable()
    private final com.lumber.inventory.data.ble.ReekonDevice connectedDevice = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.ble.MeasurementInputState measurementState = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String errorMessage = null;
    private final boolean permissionsGranted = false;
    private final boolean bluetoothEnabled = false;
    
    public ReekonUiState(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.BleConnectionState bleConnectionState, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lumber.inventory.data.ble.ReekonDevice> discoveredDevices, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonDevice connectedDevice, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementInputState measurementState, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, boolean permissionsGranted, boolean bluetoothEnabled) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.BleConnectionState getBleConnectionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lumber.inventory.data.ble.ReekonDevice> getDiscoveredDevices() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonDevice getConnectedDevice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.MeasurementInputState getMeasurementState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrorMessage() {
        return null;
    }
    
    public final boolean getPermissionsGranted() {
        return false;
    }
    
    public final boolean getBluetoothEnabled() {
        return false;
    }
    
    public ReekonUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.BleConnectionState component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lumber.inventory.data.ble.ReekonDevice> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonDevice component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.MeasurementInputState component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.ui.screens.reekon.ReekonUiState copy(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.BleConnectionState bleConnectionState, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lumber.inventory.data.ble.ReekonDevice> discoveredDevices, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonDevice connectedDevice, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementInputState measurementState, @org.jetbrains.annotations.Nullable()
    java.lang.String errorMessage, boolean permissionsGranted, boolean bluetoothEnabled) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}