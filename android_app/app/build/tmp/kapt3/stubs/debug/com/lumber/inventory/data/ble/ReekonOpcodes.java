package com.lumber.inventory.data.ble;

/**
 * Reekon opcodes for Sync Service Comm.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/lumber/inventory/data/ble/ReekonOpcodes;", "", "()V", "CANCEL_UPDATE", "", "LOCATE_TOOL", "MEASUREMENT", "NOP", "REQUEST_ALL_MEASUREMENTS", "REQUEST_MEASUREMENTS_BY_GROUP", "REQUEST_UNSYNCED_MEASUREMENTS", "RESPOND_ALL_MEASUREMENTS", "RESPOND_MEASUREMENTS_FROM_GROUP", "RESPOND_UNSYNCED_MEASUREMENTS", "SET_LASER", "START_UPDATE", "TARE_BLACK_BOX", "app_debug"})
public final class ReekonOpcodes {
    public static final int NOP = 0;
    public static final int START_UPDATE = 1;
    public static final int CANCEL_UPDATE = 2;
    public static final int LOCATE_TOOL = 3;
    public static final int MEASUREMENT = 4;
    public static final int REQUEST_UNSYNCED_MEASUREMENTS = 5;
    public static final int RESPOND_UNSYNCED_MEASUREMENTS = 6;
    public static final int REQUEST_ALL_MEASUREMENTS = 7;
    public static final int RESPOND_ALL_MEASUREMENTS = 8;
    public static final int REQUEST_MEASUREMENTS_BY_GROUP = 9;
    public static final int RESPOND_MEASUREMENTS_FROM_GROUP = 10;
    public static final int SET_LASER = 11;
    public static final int TARE_BLACK_BOX = 13;
    @org.jetbrains.annotations.NotNull()
    public static final com.lumber.inventory.data.ble.ReekonOpcodes INSTANCE = null;
    
    private ReekonOpcodes() {
        super();
    }
}