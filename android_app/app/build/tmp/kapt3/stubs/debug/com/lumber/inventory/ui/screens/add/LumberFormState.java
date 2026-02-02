package com.lumber.inventory.ui.screens.add;

import androidx.lifecycle.ViewModel;
import com.lumber.inventory.data.model.CreateLumberRequest;
import com.lumber.inventory.data.repository.LumberRepository;
import com.lumber.inventory.util.FractionUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b&\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\b\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\bH\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0089\u0001\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\bH\u00c6\u0001J\u0013\u0010,\u001a\u00020\b2\b\u0010-\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010.\u001a\u00020/H\u00d6\u0001J\t\u00100\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014\u00a8\u00061"}, d2 = {"Lcom/lumber/inventory/ui/screens/add/LumberFormState;", "", "species", "", "length", "width", "thickness", "planed", "", "locationName", "tags", "speciesError", "lengthError", "widthError", "thicknessError", "fromReekon", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getFromReekon", "()Z", "getLength", "()Ljava/lang/String;", "getLengthError", "getLocationName", "getPlaned", "getSpecies", "getSpeciesError", "getTags", "getThickness", "getThicknessError", "getWidth", "getWidthError", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
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
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String tags = null;
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
    java.lang.String locationName, @org.jetbrains.annotations.NotNull()
    java.lang.String tags, @org.jetbrains.annotations.Nullable()
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTags() {
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
    
    public final boolean component12() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.ui.screens.add.LumberFormState copy(@org.jetbrains.annotations.NotNull()
    java.lang.String species, @org.jetbrains.annotations.NotNull()
    java.lang.String length, @org.jetbrains.annotations.NotNull()
    java.lang.String width, @org.jetbrains.annotations.NotNull()
    java.lang.String thickness, boolean planed, @org.jetbrains.annotations.NotNull()
    java.lang.String locationName, @org.jetbrains.annotations.NotNull()
    java.lang.String tags, @org.jetbrains.annotations.Nullable()
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