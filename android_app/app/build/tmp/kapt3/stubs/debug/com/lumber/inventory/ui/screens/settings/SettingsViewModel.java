package com.lumber.inventory.ui.screens.settings;

import androidx.lifecycle.ViewModel;
import com.lumber.inventory.data.repository.LumberRepository;
import com.lumber.inventory.data.repository.SettingsRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ\u000e\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/lumber/inventory/ui/screens/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/lumber/inventory/data/repository/SettingsRepository;", "lumberRepository", "Lcom/lumber/inventory/data/repository/LumberRepository;", "(Lcom/lumber/inventory/data/repository/SettingsRepository;Lcom/lumber/inventory/data/repository/LumberRepository;)V", "_connectionState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/lumber/inventory/ui/screens/settings/ConnectionState;", "connectionState", "Lkotlinx/coroutines/flow/StateFlow;", "getConnectionState", "()Lkotlinx/coroutines/flow/StateFlow;", "serverUrl", "", "getServerUrl", "saveServerUrl", "", "url", "testConnection", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.lumber.inventory.data.repository.LumberRepository lumberRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> serverUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.lumber.inventory.ui.screens.settings.ConnectionState> _connectionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.settings.ConnectionState> connectionState = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.data.repository.LumberRepository lumberRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getServerUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.lumber.inventory.ui.screens.settings.ConnectionState> getConnectionState() {
        return null;
    }
    
    public final void saveServerUrl(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void testConnection(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
}