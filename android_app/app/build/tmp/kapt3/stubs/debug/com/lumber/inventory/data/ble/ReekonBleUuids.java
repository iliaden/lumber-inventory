package com.lumber.inventory.data.ble;

/**
 * Reekon BLE UUIDs from the SDK.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/lumber/inventory/data/ble/ReekonBleUuids;", "", "()V", "CCCD_UUID", "", "LIVE_MEASUREMENT_CHARACTERISTIC_UUID", "POSITION_SERVICE_UUID", "PROTOCOL_VERSION_CHARACTERISTIC_UUID", "REEKON_COMPANY_ID", "", "SYNC_COMM_CHARACTERISTIC_UUID", "SYNC_SERVICE_UUID", "app_debug"})
public final class ReekonBleUuids {
    public static final int REEKON_COMPANY_ID = 3572;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SYNC_SERVICE_UUID = "43744f76-de4d-4b05-a14a-0c133a785d8e";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SYNC_COMM_CHARACTERISTIC_UUID = "6f0b2d1e-99e2-4ef4-bfab-70a5f3890f32";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PROTOCOL_VERSION_CHARACTERISTIC_UUID = "2731f0a6-c56c-4da8-85bd-a586145ad952";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String POSITION_SERVICE_UUID = "7ad09b02-1f68-4c68-922f-a063dfdb7912";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String LIVE_MEASUREMENT_CHARACTERISTIC_UUID = "0d689f63-fdf9-4f0f-8522-736d4723ad2f";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CCCD_UUID = "00002902-0000-1000-8000-00805f9b34fb";
    @org.jetbrains.annotations.NotNull()
    public static final com.lumber.inventory.data.ble.ReekonBleUuids INSTANCE = null;
    
    private ReekonBleUuids() {
        super();
    }
}