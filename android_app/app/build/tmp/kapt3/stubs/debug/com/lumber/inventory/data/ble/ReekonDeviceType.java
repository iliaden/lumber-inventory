package com.lumber.inventory.data.ble;

/**
 * Reekon device types from the BLE SDK.
 * device_type mapping from manufacturer data.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u0010"}, d2 = {"Lcom/lumber/inventory/data/ble/ReekonDeviceType;", "", "code", "", "displayName", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getCode", "()I", "getDisplayName", "()Ljava/lang/String;", "NULL", "T1", "T1M_16FT", "T1M_25FT", "Companion", "app_debug"})
public enum ReekonDeviceType {
    /*public static final*/ NULL /* = new NULL(0, null) */,
    /*public static final*/ T1 /* = new T1(0, null) */,
    /*public static final*/ T1M_16FT /* = new T1M_16FT(0, null) */,
    /*public static final*/ T1M_25FT /* = new T1M_25FT(0, null) */;
    private final int code = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String displayName = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.lumber.inventory.data.ble.ReekonDeviceType.Companion Companion = null;
    
    ReekonDeviceType(int code, java.lang.String displayName) {
    }
    
    public final int getCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.lumber.inventory.data.ble.ReekonDeviceType> getEntries() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/lumber/inventory/data/ble/ReekonDeviceType$Companion;", "", "()V", "fromCode", "Lcom/lumber/inventory/data/ble/ReekonDeviceType;", "code", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.lumber.inventory.data.ble.ReekonDeviceType fromCode(int code) {
            return null;
        }
    }
}