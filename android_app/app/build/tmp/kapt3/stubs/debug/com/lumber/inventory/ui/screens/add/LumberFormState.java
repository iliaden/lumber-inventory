package com.lumber.inventory.ui.screens.add;

import androidx.lifecycle.ViewModel;
import com.lumber.inventory.data.model.CreateLumberRequest;
import com.lumber.inventory.data.model.Location;
import com.lumber.inventory.data.model.Tag;
import com.lumber.inventory.data.repository.LumberRepository;
import com.lumber.inventory.util.FractionUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\"\n\u0002\b/\b\u0086\b\u0018\u00002\u00020\u0001B\u00a7\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u00a2\u0006\u0002\u0010\u0014J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\bH\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\u0010\u00103\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u00c6\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u00c6\u0003J\u00b0\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0013\u001a\u00020\bH\u00c6\u0001\u00a2\u0006\u0002\u00107J\u0013\u00108\u001a\u00020\b2\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\u000bH\u00d6\u0001J\t\u0010;\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0013\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001aR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001a\u00a8\u0006<"}, d2 = {"Lcom/lumber/inventory/ui/screens/add/LumberFormState;", "", "species", "", "length", "width", "thickness", "planed", "", "locationName", "selectedLocationId", "", "selectedTagIds", "", "customTags", "speciesError", "lengthError", "widthError", "thicknessError", "fromReekon", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;Ljava/util/Set;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getCustomTags", "()Ljava/util/Set;", "getFromReekon", "()Z", "getLength", "()Ljava/lang/String;", "getLengthError", "getLocationName", "getPlaned", "getSelectedLocationId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSelectedTagIds", "getSpecies", "getSpeciesError", "getThickness", "getThicknessError", "getWidth", "getWidthError", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;Ljava/util/Set;Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/lumber/inventory/ui/screens/add/LumberFormState;", "equals", "other", "hashCode", "toString", "app_debug"})
public final class LumberFormState {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String species = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String length = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String width = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String thickness = null;
    private final boolean planed = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String locationName = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer selectedLocationId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Integer> selectedTagIds = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.String> customTags = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String speciesError = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lengthError = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String widthError = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String thicknessError = null;
    private final boolean fromReekon = false;
    
    public LumberFormState(@org.jetbrains.annotations.NotNull()
    java.lang.String species, @org.jetbrains.annotations.NotNull()
    java.lang.String length, @org.jetbrains.annotations.NotNull()
    java.lang.String width, @org.jetbrains.annotations.NotNull()
    java.lang.String thickness, boolean planed, @org.jetbrains.annotations.NotNull()
    java.lang.String locationName, @org.jetbrains.annotations.Nullable()
    java.lang.Integer selectedLocationId, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> selectedTagIds, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> customTags, @org.jetbrains.annotations.Nullable()
    java.lang.String speciesError, @org.jetbrains.annotations.Nullable()
    java.lang.String lengthError, @org.jetbrains.annotations.Nullable()
    java.lang.String widthError, @org.jetbrains.annotations.Nullable()
    java.lang.String thicknessError, boolean fromReekon) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLocationName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getSelectedLocationId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> getSelectedTagIds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getCustomTags() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getSpeciesError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLengthError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getWidthError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getThicknessError() {
        return null;
    }
    
    public final boolean getFromReekon() {
        return false;
    }
    
    public LumberFormState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    public final boolean component14() {
        return false;
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.Integer> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.ui.screens.add.LumberFormState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String species, @org.jetbrains.annotations.NotNull()
    java.lang.String length, @org.jetbrains.annotations.NotNull()
    java.lang.String width, @org.jetbrains.annotations.NotNull()
    java.lang.String thickness, boolean planed, @org.jetbrains.annotations.NotNull()
    java.lang.String locationName, @org.jetbrains.annotations.Nullable()
    java.lang.Integer selectedLocationId, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Integer> selectedTagIds, @org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> customTags, @org.jetbrains.annotations.Nullable()
    java.lang.String speciesError, @org.jetbrains.annotations.Nullable()
    java.lang.String lengthError, @org.jetbrains.annotations.Nullable()
    java.lang.String widthError, @org.jetbrains.annotations.Nullable()
    java.lang.String thicknessError, boolean fromReekon) {
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