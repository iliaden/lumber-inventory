package com.lumber.inventory.ui.screens.inventory;

import androidx.compose.foundation.layout.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.style.TextOverflow;
import com.lumber.inventory.R;
import com.lumber.inventory.data.model.Lumber;
import com.lumber.inventory.util.FractionUtils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001a(\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u001aP\u0010\t\u001a\u00020\u00012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0007\u001a(\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0003\u00a8\u0006\u0016"}, d2 = {"EmptyState", "", "modifier", "Landroidx/compose/ui/Modifier;", "ErrorState", "message", "", "onRetry", "Lkotlin/Function0;", "InventoryScreen", "onAddClick", "onEditClick", "Lkotlin/Function1;", "", "onFilterClick", "onSettingsClick", "viewModel", "Lcom/lumber/inventory/ui/screens/inventory/InventoryViewModel;", "LumberCard", "lumber", "Lcom/lumber/inventory/data/model/Lumber;", "onClick", "app_debug"})
public final class InventoryScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void InventoryScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onAddClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFilterClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSettingsClick, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.ui.screens.inventory.InventoryViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LumberCard(com.lumber.inventory.data.model.Lumber lumber, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyState(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ErrorState(java.lang.String message, kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, androidx.compose.ui.Modifier modifier) {
    }
}