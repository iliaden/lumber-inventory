package com.lumber.inventory.ui.screens.inventory;

import androidx.lifecycle.ViewModel;
import com.lumber.inventory.data.model.Lumber;
import com.lumber.inventory.data.model.LumberFilter;
import com.lumber.inventory.data.repository.LumberRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\u000fJ\u0010\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\tJ\u0006\u0010\u0013\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0014"}, d2 = {"Lcom/lumber/inventory/ui/screens/inventory/InventoryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/lumber/inventory/data/repository/LumberRepository;", "(Lcom/lumber/inventory/data/repository/LumberRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/lumber/inventory/ui/screens/inventory/InventoryUiState;", "currentFilter", "Lcom/lumber/inventory/data/model/LumberFilter;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "applyFilter", "", "filter", "clearFilter", "loadLumber", "refresh", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class InventoryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.repository.LumberRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.inventory.InventoryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.inventory.InventoryUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private com.lumber.inventory.data.model.LumberFilter currentFilter;
    
    @javax.inject.Inject()
    public InventoryViewModel(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.repository.LumberRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.inventory.InventoryUiState> getUiState() {
        return null;
    }
    
    public final void loadLumber(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.LumberFilter filter) {
    }
    
    public final void refresh() {
    }
    
    public final void applyFilter(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.model.LumberFilter filter) {
    }
    
    public final void clearFilter() {
    }
}