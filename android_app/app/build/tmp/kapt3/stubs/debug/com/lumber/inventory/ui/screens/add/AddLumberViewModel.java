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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0015H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0006\u0010\u001a\u001a\u00020\u0015J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u001c\u001a\u00020\u0015J\u0006\u0010\u001d\u001a\u00020\u0015J\u0010\u0010\u001e\u001a\u00020\u00152\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u001e\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#J\u000e\u0010&\u001a\u00020\u00152\u0006\u0010\'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0017J\u000e\u0010+\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0017J\u000e\u0010,\u001a\u00020\u00152\u0006\u0010*\u001a\u00020-J\u000e\u0010.\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0017J\u000e\u0010/\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0017J\u000e\u00100\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0017J\b\u00101\u001a\u00020-H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f\u00a8\u00062"}, d2 = {"Lcom/lumber/inventory/ui/screens/add/AddLumberViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/lumber/inventory/data/repository/LumberRepository;", "(Lcom/lumber/inventory/data/repository/LumberRepository;)V", "_dropdownData", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/lumber/inventory/ui/screens/add/DropdownData;", "_formState", "Lcom/lumber/inventory/ui/screens/add/LumberFormState;", "_uiState", "Lcom/lumber/inventory/ui/screens/add/AddLumberUiState;", "dropdownData", "Lkotlinx/coroutines/flow/StateFlow;", "getDropdownData", "()Lkotlinx/coroutines/flow/StateFlow;", "formState", "getFormState", "uiState", "getUiState", "addCustomTag", "", "tagName", "", "clearMeasurementsOnly", "loadDropdownData", "refreshDropdownData", "removeCustomTag", "resetUiState", "saveLumber", "selectLocation", "location", "Lcom/lumber/inventory/data/model/Location;", "setInitialMeasurements", "lengthInches", "", "widthInches", "thicknessInches", "toggleTagSelection", "tag", "Lcom/lumber/inventory/data/model/Tag;", "updateLength", "value", "updateLocationName", "updatePlaned", "", "updateSpecies", "updateThickness", "updateWidth", "validateForm", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class AddLumberViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.repository.LumberRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.add.LumberFormState> _formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.add.LumberFormState> formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.add.AddLumberUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.add.AddLumberUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.add.DropdownData> _dropdownData = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.add.DropdownData> dropdownData = null;
    
    @javax.inject.Inject()
    public AddLumberViewModel(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.repository.LumberRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.add.LumberFormState> getFormState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.add.AddLumberUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.add.DropdownData> getDropdownData() {
        return null;
    }
    
    private final void loadDropdownData() {
    }
    
    public final void refreshDropdownData() {
    }
    
    /**
     * Set initial measurements from Reekon device.
     * Converts inches to fractional display strings.
     */
    public final void setInitialMeasurements(double lengthInches, double widthInches, double thicknessInches) {
    }
    
    public final void updateSpecies(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateLength(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateWidth(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updateThickness(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void updatePlaned(boolean value) {
    }
    
    public final void updateLocationName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void selectLocation(@org.jetbrains.annotations.Nullable()
    com.lumber.inventory.data.model.Location location) {
    }
    
    public final void toggleTagSelection(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.Tag tag) {
    }
    
    public final void addCustomTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tagName) {
    }
    
    public final void removeCustomTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tagName) {
    }
    
    private final boolean validateForm() {
        return false;
    }
    
    /**
     * Clear only the measurement fields (length, width, thickness) while preserving
     * species, location, tags, and planed status for bulk entry.
     */
    private final void clearMeasurementsOnly() {
    }
    
    /**
     * Reset the UI state back to Idle so user can continue adding more items.
     */
    public final void resetUiState() {
    }
    
    public final void saveLumber() {
    }
}