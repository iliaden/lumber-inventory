package com.lumber.inventory.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Request body for creating a new lumber item.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b \b\u0086\b\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u0012\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0011\u0010%\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\rH\u00c6\u0003J\u0011\u0010&\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\rH\u00c6\u0003J|\u0010\'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r2\u0010\b\u0002\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\rH\u00c6\u0001\u00a2\u0006\u0002\u0010(J\u0013\u0010)\u001a\u00020\b2\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020\nH\u00d6\u0001J\t\u0010,\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u001e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\r8\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/lumber/inventory/data/model/CreateLumberRequest;", "", "species", "", "length", "width", "thickness", "planed", "", "locationId", "", "locationName", "tags", "", "tagIds", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getLength", "()Ljava/lang/String;", "getLocationId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLocationName", "getPlaned", "()Z", "getSpecies", "getTagIds", "()Ljava/util/List;", "getTags", "getThickness", "getWidth", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)Lcom/lumber/inventory/data/model/CreateLumberRequest;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class CreateLumberRequest {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String species = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String length = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String width = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thickness = null;
    private final boolean planed = false;
    @com.google.gson.annotations.SerializedName(value = "location_id")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer locationId = null;
    @com.google.gson.annotations.SerializedName(value = "location_name")
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String locationName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.String> tags = null;
    @com.google.gson.annotations.SerializedName(value = "tag_ids")
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.Integer> tagIds = null;
    
    public CreateLumberRequest(@org.jetbrains.annotations.NotNull()
    java.lang.String species, @org.jetbrains.annotations.NotNull()
    java.lang.String length, @org.jetbrains.annotations.NotNull()
    java.lang.String width, @org.jetbrains.annotations.NotNull()
    java.lang.String thickness, boolean planed, @org.jetbrains.annotations.Nullable()
    java.lang.Integer locationId, @org.jetbrains.annotations.Nullable()
    java.lang.String locationName, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> tagIds) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSpecies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLength() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getWidth() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThickness() {
        return null;
    }
    
    public final boolean getPlaned() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getLocationId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLocationName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> getTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.Integer> getTagIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.Integer> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.data.model.CreateLumberRequest copy(@org.jetbrains.annotations.NotNull()
    java.lang.String species, @org.jetbrains.annotations.NotNull()
    java.lang.String length, @org.jetbrains.annotations.NotNull()
    java.lang.String width, @org.jetbrains.annotations.NotNull()
    java.lang.String thickness, boolean planed, @org.jetbrains.annotations.Nullable()
    java.lang.Integer locationId, @org.jetbrains.annotations.Nullable()
    java.lang.String locationName, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> tagIds) {
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