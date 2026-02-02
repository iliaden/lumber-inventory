package com.lumber.inventory.ui.screens.add;

import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material.icons.Icons;
import androidx.compose.material3.*;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.input.KeyboardType;
import com.lumber.inventory.R;
import com.lumber.inventory.data.model.Location;
import com.lumber.inventory.data.model.Tag;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\u001aW\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u00a2\u0006\u0002\u0010\u000b\u001aW\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0014\u0010\u0013\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016H\u0003\u00a2\u0006\u0002\u0010\u0017\u001aD\u0010\u0018\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000eH\u0003\u001an\u0010\u001d\u001a\u00020\u00012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00162\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0!2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u00102\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00102\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0010H\u0003\u00a8\u0006&"}, d2 = {"AddLumberScreen", "", "onNavigateBack", "Lkotlin/Function0;", "onMeasureWithReekon", "initialLength", "", "initialWidth", "initialThickness", "viewModel", "Lcom/lumber/inventory/ui/screens/add/AddLumberViewModel;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;Lcom/lumber/inventory/ui/screens/add/AddLumberViewModel;)V", "LocationDropdownField", "value", "", "onValueChange", "Lkotlin/Function1;", "selectedLocationId", "", "onLocationSelected", "Lcom/lumber/inventory/data/model/Location;", "locations", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/Integer;Lkotlin/jvm/functions/Function1;Ljava/util/List;)V", "SpeciesDropdownField", "existingSpecies", "isError", "", "errorMessage", "TagsSelectionField", "availableTags", "Lcom/lumber/inventory/data/model/Tag;", "selectedTagIds", "", "customTags", "onTagToggled", "onCustomTagAdded", "onCustomTagRemoved", "app_debug"})
public final class AddLumberScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    public static final void AddLumberScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onMeasureWithReekon, @org.jetbrains.annotations.Nullable()
    java.lang.Double initialLength, @org.jetbrains.annotations.Nullable()
    java.lang.Double initialWidth, @org.jetbrains.annotations.Nullable()
    java.lang.Double initialThickness, @org.jetbrains.annotations.NotNull()
    com.lumber.inventory.ui.screens.add.AddLumberViewModel viewModel) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void SpeciesDropdownField(java.lang.String value, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onValueChange, java.util.List<java.lang.String> existingSpecies, boolean isError, java.lang.String errorMessage) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    private static final void LocationDropdownField(java.lang.String value, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onValueChange, java.lang.Integer selectedLocationId, kotlin.jvm.functions.Function1<? super com.lumber.inventory.data.model.Location, kotlin.Unit> onLocationSelected, java.util.List<com.lumber.inventory.data.model.Location> locations) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    private static final void TagsSelectionField(java.util.List<com.lumber.inventory.data.model.Tag> availableTags, java.util.Set<java.lang.Integer> selectedTagIds, java.util.Set<java.lang.String> customTags, kotlin.jvm.functions.Function1<? super com.lumber.inventory.data.model.Tag, kotlin.Unit> onTagToggled, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCustomTagAdded, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onCustomTagRemoved) {
    }
}