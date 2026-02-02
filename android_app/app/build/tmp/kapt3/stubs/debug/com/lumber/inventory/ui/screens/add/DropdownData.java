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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\nH\u00c6\u0003JC\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\n2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001J\t\u0010\u001a\u001a\u00020\u0004H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e\u00a8\u0006\u001b"}, d2 = {"Lcom/lumber/inventory/ui/screens/add/DropdownData;", "", "species", "", "", "locations", "Lcom/lumber/inventory/data/model/Location;", "tags", "Lcom/lumber/inventory/data/model/Tag;", "isLoading", "", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Z)V", "()Z", "getLocations", "()Ljava/util/List;", "getSpecies", "getTags", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class DropdownData {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> species = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.lumber.inventory.data.model.Location> locations = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.lumber.inventory.data.model.Tag> tags = null;
    private final boolean isLoading = false;
    
    public DropdownData(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> species, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lumber.inventory.data.model.Location> locations, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lumber.inventory.data.model.Tag> tags, boolean isLoading) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSpecies() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lumber.inventory.data.model.Location> getLocations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lumber.inventory.data.model.Tag> getTags() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public DropdownData() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lumber.inventory.data.model.Location> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.lumber.inventory.data.model.Tag> component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.lumber.inventory.ui.screens.add.DropdownData copy(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> species, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lumber.inventory.data.model.Location> locations, @org.jetbrains.annotations.NotNull()
    java.util.List<com.lumber.inventory.data.model.Tag> tags, boolean isLoading) {
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