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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001a\u001a\u00020\u000eJ\u0006\u0010\u001b\u001a\u00020\u000eJ\u000e\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u000eH\u0002J\b\u0010 \u001a\u00020\u000eH\u0002J\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#J\u0006\u0010$\u001a\u00020\u000eJ\u0011\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0&\u00a2\u0006\u0002\u0010\'J\u0006\u0010(\u001a\u00020\u0010J\u0006\u0010)\u001a\u00020\u000eJ\b\u0010*\u001a\u00020\u000eH\u0014J\u000e\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020\u0010J\u0006\u0010-\u001a\u00020\u000eJ\u000e\u0010.\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010/\u001a\u00020\u000eJ\u0006\u00100\u001a\u00020\u000eR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u00061"}, d2 = {"Lcom/lumber/inventory/ui/screens/reekon/ReekonMeasurementViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "bleManager", "Lcom/lumber/inventory/data/ble/ReekonBleManager;", "measurementInputManager", "Lcom/lumber/inventory/data/ble/MeasurementInputManager;", "(Landroid/content/Context;Lcom/lumber/inventory/data/ble/ReekonBleManager;Lcom/lumber/inventory/data/ble/MeasurementInputManager;)V", "_errorMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_measurementAccepted", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "_permissionsGranted", "", "measurementAccepted", "Lkotlinx/coroutines/flow/SharedFlow;", "getMeasurementAccepted", "()Lkotlinx/coroutines/flow/SharedFlow;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/lumber/inventory/ui/screens/reekon/ReekonUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "checkPermissions", "clearError", "clearSlot", "slot", "Lcom/lumber/inventory/data/ble/MeasurementSlot;", "collectBleErrors", "collectMeasurements", "connectToDevice", "device", "Lcom/lumber/inventory/data/ble/ReekonDevice;", "disconnect", "getRequiredPermissions", "", "()[Ljava/lang/String;", "isBleSupported", "locateTool", "onCleared", "onPermissionsResult", "granted", "resetMeasurements", "setCurrentSlot", "startScan", "stopScan", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReekonMeasurementViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.ble.ReekonBleManager bleManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.ble.MeasurementInputManager measurementInputManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _permissionsGranted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Unit> _measurementAccepted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<kotlin.Unit> measurementAccepted = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.reekon.ReekonUiState> uiState = null;
    
    @javax.inject.Inject()
    public ReekonMeasurementViewModel(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.ReekonBleManager bleManager, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementInputManager measurementInputManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<kotlin.Unit> getMeasurementAccepted() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.reekon.ReekonUiState> getUiState() {
        return null;
    }
    
    private final void collectBleErrors() {
    }
    
    private final void collectMeasurements() {
    }
    
    public final void checkPermissions() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getRequiredPermissions() {
        return null;
    }
    
    public final void onPermissionsResult(boolean granted) {
    }
    
    public final void startScan() {
    }
    
    public final void stopScan() {
    }
    
    public final void connectToDevice(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.ReekonDevice device) {
    }
    
    public final void disconnect() {
    }
    
    public final void locateTool() {
    }
    
    public final void setCurrentSlot(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementSlot slot) {
    }
    
    public final void clearSlot(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementSlot slot) {
    }
    
    public final void resetMeasurements() {
    }
    
    public final void clearError() {
    }
    
    public final boolean isBleSupported() {
        return false;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}