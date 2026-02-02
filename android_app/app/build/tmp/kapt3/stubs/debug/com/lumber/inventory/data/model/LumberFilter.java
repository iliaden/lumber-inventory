package com.lumber.inventory.data.model;

/**
 * Represents filter criteria for searching lumber inventory.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b%\b\u0086\b\u0018\u0000 +2\u00020\u0001:\u0001+Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\rJ\u0006\u0010\u001b\u001a\u00020\u0005J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0017J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003Jn\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00072\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020\u0005H\u00d6\u0001J\u0006\u0010)\u001a\u00020\u0007J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u001a\u0010\u000f\u00a8\u0006,"}, d2 = {"Lcom/lumber/inventory/data/model/LumberFilter;", "", "species", "", "locationId", "", "planed", "", "tagId", "minLength", "maxLength", "minThickness", "maxThickness", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLocationId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMaxLength", "()Ljava/lang/String;", "getMaxThickness", "getMinLength", "getMinThickness", "getPlaned", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getSpecies", "getTagId", "activeCount", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/lumber/inventory/data/model/LumberFilter;", "equals", "other", "hashCode", "isActive", "toString", "Companion", "app_debug"})
public final class LumberFilter {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String species = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer locationId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean planed = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer tagId = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String minLength = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String maxLength = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String minThickness = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String maxThickness = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.lumber.inventory.data.model.LumberFilter EMPTY = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.lumber.inventory.data.model.LumberFilter.Companion Companion = null;
    
    public LumberFilter(@org.jetbrains.annotations.Nullable()
    java.lang.String species, @org.jetbrains.annotations.Nullable()
    java.lang.Integer locationId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean planed, @org.jetbrains.annotations.Nullable()
    java.lang.Integer tagId, @org.jetbrains.annotations.Nullable()
    java.lang.String minLength, @org.jetbrains.annotations.Nullable()
    java.lang.String maxLength, @org.jetbrains.annotations.Nullable()
    java.lang.String minThickness, @org.jetbrains.annotations.Nullable()
    java.lang.String maxThickness) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSpecies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getLocationId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean getPlaned() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTagId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMinLength() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMaxLength() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMinThickness() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getMaxThickness() {
        return null;
    }
    
    /**
     * Returns true if any filter is active.
     */
    public final boolean isActive() {
        return false;
    }
    
    /**
     * Returns the number of active filters.
     */
    public final int activeCount() {
        return 0;
    }
    
    public LumberFilter() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.model.LumberFilter copy(@org.jetbrains.annotations.Nullable()
    java.lang.String species, @org.jetbrains.annotations.Nullable()
    java.lang.Integer locationId, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean planed, @org.jetbrains.annotations.Nullable()
    java.lang.Integer tagId, @org.jetbrains.annotations.Nullable()
    java.lang.String minLength, @org.jetbrains.annotations.Nullable()
    java.lang.String maxLength, @org.jetbrains.annotations.Nullable()
    java.lang.String minThickness, @org.jetbrains.annotations.Nullable()
    java.lang.String maxThickness) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/lumber/inventory/data/model/LumberFilter$Companion;", "", "()V", "EMPTY", "Lcom/lumber/inventory/data/model/LumberFilter;", "getEMPTY", "()Lcom/lumber/inventory/data/model/LumberFilter;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.lumber.inventory.data.model.LumberFilter getEMPTY() {
            return null;
        }
    }
}