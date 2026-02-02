package com.lumber.inventory.data.ble;

import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * State for collecting 3 measurements from a Reekon device.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0018\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u000b\u0010(\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\nH\u00c6\u0003JM\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010/\u001a\u00020\n2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u00020\u000fH\u00d6\u0001J\t\u00102\u001a\u00020\u0017H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0016\u001a\u00020\u00178F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u001b8F\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u001f\u001a\u00020\u00178F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0019R\u0013\u0010!\u001a\u0004\u0018\u00010\u001b8F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001dR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0014R\u0011\u0010$\u001a\u00020\u00178F\u00a2\u0006\u0006\u001a\u0004\b%\u0010\u0019R\u0013\u0010&\u001a\u0004\u0018\u00010\u001b8F\u00a2\u0006\u0006\u001a\u0004\b\'\u0010\u001d\u00a8\u00063"}, d2 = {"Lcom/lumber/inventory/data/ble/MeasurementInputState;", "", "length", "Lcom/lumber/inventory/data/ble/ReekonMeasurement;", "width", "thickness", "currentSlot", "Lcom/lumber/inventory/data/ble/MeasurementSlot;", "lastMeasurement", "isComplete", "", "(Lcom/lumber/inventory/data/ble/ReekonMeasurement;Lcom/lumber/inventory/data/ble/ReekonMeasurement;Lcom/lumber/inventory/data/ble/ReekonMeasurement;Lcom/lumber/inventory/data/ble/MeasurementSlot;Lcom/lumber/inventory/data/ble/ReekonMeasurement;Z)V", "getCurrentSlot", "()Lcom/lumber/inventory/data/ble/MeasurementSlot;", "filledSlots", "", "getFilledSlots", "()I", "()Z", "getLastMeasurement", "()Lcom/lumber/inventory/data/ble/ReekonMeasurement;", "getLength", "lengthDisplay", "", "getLengthDisplay", "()Ljava/lang/String;", "lengthInches", "", "getLengthInches", "()Ljava/lang/Double;", "getThickness", "thicknessDisplay", "getThicknessDisplay", "thicknessInches", "getThicknessInches", "getWidth", "widthDisplay", "getWidthDisplay", "widthInches", "getWidthInches", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class MeasurementInputState {
    @org.jetbrains.annotations.Nullable()
    private final com.lumber.inventory.data.ble.ReekonMeasurement length = null;
    @org.jetbrains.annotations.Nullable()
    private final com.lumber.inventory.data.ble.ReekonMeasurement width = null;
    @org.jetbrains.annotations.Nullable()
    private final com.lumber.inventory.data.ble.ReekonMeasurement thickness = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.ble.MeasurementSlot currentSlot = null;
    @org.jetbrains.annotations.Nullable()
    private final com.lumber.inventory.data.ble.ReekonMeasurement lastMeasurement = null;
    private final boolean isComplete = false;
    
    public MeasurementInputState(@org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement length, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement width, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement thickness, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementSlot currentSlot, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement lastMeasurement, boolean isComplete) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement getLength() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement getWidth() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement getThickness() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.MeasurementSlot getCurrentSlot() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement getLastMeasurement() {
        return null;
    }
    
    public final boolean isComplete() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLengthInches() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getWidthInches() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getThicknessInches() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLengthDisplay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWidthDisplay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThicknessDisplay() {
        return null;
    }
    
    public final int getFilledSlots() {
        return 0;
    }
    
    public MeasurementInputState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.MeasurementSlot component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.lumber.inventory.data.ble.ReekonMeasurement component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.MeasurementInputState copy(@org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement length, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement width, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement thickness, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.ble.MeasurementSlot currentSlot, @org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.ble.ReekonMeasurement lastMeasurement, boolean isComplete) {
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