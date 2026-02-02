package com.lumber.inventory.data.ble;

/**
 * Represents a measurement received from a Reekon tool.
 * Based on the measurement record layout (opcode 4 payload).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0017\b\u0086\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0006H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0006H\u00c6\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010-\u001a\u0004\u0018\u00010\u0006H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003Jb\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u00100J\u0013\u00101\u001a\u00020\u00152\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0010\u00103\u001a\u00020\u001c2\u0006\u00104\u001a\u00020 H\u0002J\t\u00105\u001a\u00020\u0006H\u00d6\u0001J\t\u00106\u001a\u00020\u001cH\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0014\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0018\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u00158F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0016R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\u001b\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010\u00a8\u00067"}, d2 = {"Lcom/lumber/inventory/data/ble/ReekonMeasurement;", "", "uuid", "", "flags", "positionUm", "", "zeroDeltaUm", "activeOffsetUm", "groupNumber", "measurementNumber", "timestamp", "(JJIJILjava/lang/Integer;Ljava/lang/Integer;J)V", "getActiveOffsetUm", "()I", "getFlags", "()J", "getGroupNumber", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "isCenterMode", "", "()Z", "isMeasureBackMode", "isRelativeMode", "isSynced", "getMeasurementNumber", "positionFeetInches", "", "getPositionFeetInches", "()Ljava/lang/String;", "positionInches", "", "getPositionInches", "()D", "getPositionUm", "getTimestamp", "getUuid", "getZeroDeltaUm", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(JJIJILjava/lang/Integer;Ljava/lang/Integer;J)Lcom/lumber/inventory/data/ble/ReekonMeasurement;", "equals", "other", "formatInches", "inches", "hashCode", "toString", "app_debug"})
public final class ReekonMeasurement {
    private final long uuid = 0L;
    private final long flags = 0L;
    private final int positionUm = 0;
    private final long zeroDeltaUm = 0L;
    private final int activeOffsetUm = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer groupNumber = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer measurementNumber = null;
    private final long timestamp = 0L;
    
    public ReekonMeasurement(long uuid, long flags, int positionUm, long zeroDeltaUm, int activeOffsetUm, @org.jetbrains.annotations.Nullable()
    java.lang.Integer groupNumber, @org.jetbrains.annotations.Nullable()
    java.lang.Integer measurementNumber, long timestamp) {
        super();
    }
    
    public final long getUuid() {
        return 0L;
    }
    
    public final long getFlags() {
        return 0L;
    }
    
    public final int getPositionUm() {
        return 0;
    }
    
    public final long getZeroDeltaUm() {
        return 0L;
    }
    
    public final int getActiveOffsetUm() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getGroupNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getMeasurementNumber() {
        return null;
    }
    
    public final long getTimestamp() {
        return 0L;
    }
    
    public final double getPositionInches() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPositionFeetInches() {
        return null;
    }
    
    public final boolean isRelativeMode() {
        return false;
    }
    
    public final boolean isMeasureBackMode() {
        return false;
    }
    
    public final boolean isCenterMode() {
        return false;
    }
    
    public final boolean isSynced() {
        return false;
    }
    
    private final java.lang.String formatInches(double inches) {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final int component5() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.ble.ReekonMeasurement copy(long uuid, long flags, int positionUm, long zeroDeltaUm, int activeOffsetUm, @org.jetbrains.annotations.Nullable()
    java.lang.Integer groupNumber, @org.jetbrains.annotations.Nullable()
    java.lang.Integer measurementNumber, long timestamp) {
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