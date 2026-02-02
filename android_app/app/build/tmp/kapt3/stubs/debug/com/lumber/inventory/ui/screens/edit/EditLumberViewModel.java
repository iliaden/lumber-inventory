package com.lumber.inventory.ui.screens.edit;

import androidx.lifecycle.ViewModel;
import com.lumber.inventory.data.model.UpdateLumberRequest;
import com.lumber.inventory.data.repository.LumberRepository;
import com.lumber.inventory.util.FractionUtils;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000fJ\u0006\u0010\u0016\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010!\u001a\u00020\u001cH\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\r\u00a8\u0006\""}, d2 = {"Lcom/lumber/inventory/ui/screens/edit/EditLumberViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/lumber/inventory/data/repository/LumberRepository;", "(Lcom/lumber/inventory/data/repository/LumberRepository;)V", "_formState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/lumber/inventory/ui/screens/edit/EditLumberFormState;", "_uiState", "Lcom/lumber/inventory/ui/screens/edit/EditLumberUiState;", "formState", "Lkotlinx/coroutines/flow/StateFlow;", "getFormState", "()Lkotlinx/coroutines/flow/StateFlow;", "lumberId", "", "uiState", "getUiState", "deleteLumber", "", "loadLumber", "id", "saveLumber", "updateLength", "value", "", "updateLocationName", "updatePlaned", "", "updateSpecies", "updateTags", "updateThickness", "updateWidth", "validateForm", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class EditLumberViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.repository.LumberRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.edit.EditLumberFormState> _formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.edit.EditLumberFormState> formState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.edit.EditLumberUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.edit.EditLumberUiState> uiState = null;
    private int lumberId = -1;
    
    @javax.inject.Inject()
    public EditLumberViewModel(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.repository.LumberRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.edit.EditLumberFormState> getFormState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.edit.EditLumberUiState> getUiState() {
        return null;
    }
    
    public final void loadLumber(int id) {
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
    
    public final void updateTags(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    private final boolean validateForm() {
        return false;
    }
    
    public final void saveLumber() {
    }
    
    public final void deleteLumber() {
    }
}