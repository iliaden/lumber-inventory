package com.lumber.inventory.data.ble;

import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Represents the three dimensions being measured for a lumber piece.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/lumber/inventory/data/ble/MeasurementSlot;", "", "(Ljava/lang/String;I)V", "LENGTH", "WIDTH", "THICKNESS", "app_debug"})
public enum MeasurementSlot {
    /*public static final*/ LENGTH /* = new LENGTH() */,
    /*public static final*/ WIDTH /* = new WIDTH() */,
    /*public static final*/ THICKNESS /* = new THICKNESS() */;
    
    MeasurementSlot() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.lumber.inventory.data.ble.MeasurementSlot> getEntries() {
        return null;
    }
}