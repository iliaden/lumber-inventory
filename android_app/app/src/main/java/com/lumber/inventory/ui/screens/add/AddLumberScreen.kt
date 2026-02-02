package com.lumber.inventory.ui.screens.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lumber.inventory.R
import com.lumber.inventory.data.model.Location
import com.lumber.inventory.data.model.Tag

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddLumberScreen(
    onNavigateBack: () -> Unit,
    onMeasureWithReekon: () -> Unit,
    initialLength: Double? = null,
    initialWidth: Double? = null,
    initialThickness: Double? = null,
    viewModel: AddLumberViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val dropdownData by viewModel.dropdownData.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    // Apply initial measurements from Reekon if provided
    LaunchedEffect(initialLength, initialWidth, initialThickness) {
        if (initialLength != null && initialWidth != null && initialThickness != null &&
            initialLength > 0 && initialWidth > 0 && initialThickness > 0) {
            viewModel.setInitialMeasurements(initialLength, initialWidth, initialThickness)
        }
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is AddLumberUiState.Success -> {
                snackbarHostState.showSnackbar(
                    message = "Lumber added successfully!",
                    duration = SnackbarDuration.Short
                )
                // Reset state to Idle so user can continue adding
                viewModel.resetUiState()
            }
            is AddLumberUiState.Error -> {
                snackbarHostState.showSnackbar(
                    message = (uiState as AddLumberUiState.Error).message
                )
                viewModel.resetUiState()
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_add_lumber)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Species Dropdown with autocomplete
            SpeciesDropdownField(
                value = formState.species,
                onValueChange = { viewModel.updateSpecies(it) },
                existingSpecies = dropdownData.species,
                isError = formState.speciesError != null,
                errorMessage = formState.speciesError
            )

            // Reekon Measurement Button
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (formState.fromReekon)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Straighten,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dimensions",
                            style = MaterialTheme.typography.titleSmall
                        )
                        if (formState.fromReekon) {
                            Text(
                                text = "Measured with Reekon T1M",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(
                                text = "Enter manually or use Reekon",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                    FilledTonalButton(
                        onClick = onMeasureWithReekon
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bluetooth,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(if (formState.fromReekon) "Re-measure" else "Reekon T1M")
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = formState.length,
                    onValueChange = { viewModel.updateLength(it) },
                    label = { Text(stringResource(R.string.label_length)) },
                    placeholder = { Text(stringResource(R.string.hint_dimension)) },
                    isError = formState.lengthError != null,
                    supportingText = formState.lengthError?.let { { Text(it) } },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )

                OutlinedTextField(
                    value = formState.width,
                    onValueChange = { viewModel.updateWidth(it) },
                    label = { Text(stringResource(R.string.label_width)) },
                    placeholder = { Text(stringResource(R.string.hint_dimension)) },
                    isError = formState.widthError != null,
                    supportingText = formState.widthError?.let { { Text(it) } },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                )
            }

            OutlinedTextField(
                value = formState.thickness,
                onValueChange = { viewModel.updateThickness(it) },
                label = { Text(stringResource(R.string.label_thickness)) },
                placeholder = { Text(stringResource(R.string.hint_dimension)) },
                isError = formState.thicknessError != null,
                supportingText = formState.thicknessError?.let { { Text(it) } },
                modifier = Modifier.fillMaxWidth(0.5f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilterChip(
                    selected = !formState.planed,
                    onClick = { viewModel.updatePlaned(false) },
                    label = { Text(stringResource(R.string.label_rough)) }
                )
                FilterChip(
                    selected = formState.planed,
                    onClick = { viewModel.updatePlaned(true) },
                    label = { Text(stringResource(R.string.label_planed)) }
                )
            }

            // Location Dropdown
            LocationDropdownField(
                value = formState.locationName,
                onValueChange = { viewModel.updateLocationName(it) },
                selectedLocationId = formState.selectedLocationId,
                onLocationSelected = { viewModel.selectLocation(it) },
                locations = dropdownData.locations
            )

            // Tags Selection
            TagsSelectionField(
                availableTags = dropdownData.tags,
                selectedTagIds = formState.selectedTagIds,
                customTags = formState.customTags,
                onTagToggled = { viewModel.toggleTagSelection(it) },
                onCustomTagAdded = { viewModel.addCustomTag(it) },
                onCustomTagRemoved = { viewModel.removeCustomTag(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.btn_cancel))
                }

                Button(
                    onClick = { viewModel.saveLumber() },
                    enabled = uiState !is AddLumberUiState.Loading,
                    modifier = Modifier.weight(1f)
                ) {
                    if (uiState is AddLumberUiState.Loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    } else {
                        Text(stringResource(R.string.btn_save))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SpeciesDropdownField(
    value: String,
    onValueChange: (String) -> Unit,
    existingSpecies: List<String>,
    isError: Boolean,
    errorMessage: String?
) {
    var expanded by remember { mutableStateOf(false) }
    val filteredSpecies = remember(value, existingSpecies) {
        if (value.isBlank()) {
            existingSpecies
        } else {
            existingSpecies.filter { it.contains(value, ignoreCase = true) }
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded && filteredSpecies.isNotEmpty(),
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            label = { Text(stringResource(R.string.label_species)) },
            placeholder = { Text(stringResource(R.string.hint_species)) },
            isError = isError,
            supportingText = if (errorMessage != null) {
                { Text(errorMessage) }
            } else if (existingSpecies.isNotEmpty() && value.isBlank()) {
                { Text("Select from list or type new species") }
            } else null,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            singleLine = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded && filteredSpecies.isNotEmpty())
            }
        )

        if (filteredSpecies.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                filteredSpecies.forEach { species ->
                    DropdownMenuItem(
                        text = { Text(species) },
                        onClick = {
                            onValueChange(species)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationDropdownField(
    value: String,
    onValueChange: (String) -> Unit,
    selectedLocationId: Int?,
    onLocationSelected: (Location?) -> Unit,
    locations: List<Location>
) {
    var expanded by remember { mutableStateOf(false) }
    val filteredLocations = remember(value, locations, selectedLocationId) {
        if (value.isBlank() || selectedLocationId != null) {
            locations
        } else {
            locations.filter { it.name.contains(value, ignoreCase = true) }
        }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            label = { Text(stringResource(R.string.label_location)) },
            placeholder = { Text(stringResource(R.string.hint_new_location)) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            singleLine = true,
            supportingText = if (locations.isNotEmpty() && value.isBlank()) {
                { Text("Select from list or type new location") }
            } else if (selectedLocationId == null && value.isNotBlank() && !locations.any { it.name.equals(value, ignoreCase = true) }) {
                { Text("Will create new location") }
            } else null,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Option to clear selection
            if (selectedLocationId != null) {
                DropdownMenuItem(
                    text = {
                        Text(
                            "Clear selection",
                            color = MaterialTheme.colorScheme.error
                        )
                    },
                    onClick = {
                        onLocationSelected(null)
                        expanded = false
                    }
                )
                HorizontalDivider()
            }

            if (filteredLocations.isEmpty()) {
                DropdownMenuItem(
                    text = { Text("No matching locations") },
                    onClick = { },
                    enabled = false
                )
            } else {
                filteredLocations.forEach { location ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                location.name,
                                color = if (location.id == selectedLocationId)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurface
                            )
                        },
                        onClick = {
                            onLocationSelected(location)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagsSelectionField(
    availableTags: List<Tag>,
    selectedTagIds: Set<Int>,
    customTags: Set<String>,
    onTagToggled: (Tag) -> Unit,
    onCustomTagAdded: (String) -> Unit,
    onCustomTagRemoved: (String) -> Unit
) {
    var newTagText by remember { mutableStateOf("") }
    var showAddField by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.label_tags),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Existing tags as filter chips
        if (availableTags.isNotEmpty()) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                availableTags.forEach { tag ->
                    FilterChip(
                        selected = selectedTagIds.contains(tag.id),
                        onClick = { onTagToggled(tag) },
                        label = { Text(tag.name) }
                    )
                }
            }
        }

        // Custom tags (new tags that will be created)
        if (customTags.isNotEmpty()) {
            Text(
                text = "New tags to create:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                customTags.forEach { tagName ->
                    InputChip(
                        selected = true,
                        onClick = { onCustomTagRemoved(tagName) },
                        label = { Text(tagName) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remove",
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    )
                }
            }
        }

        // Add new tag button/field
        if (showAddField) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newTagText,
                    onValueChange = { newTagText = it },
                    label = { Text("New tag name") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                IconButton(
                    onClick = {
                        if (newTagText.isNotBlank()) {
                            // Check if this tag already exists
                            val existingTag = availableTags.find {
                                it.name.equals(newTagText.trim(), ignoreCase = true)
                            }
                            if (existingTag != null) {
                                // Select existing tag instead
                                if (!selectedTagIds.contains(existingTag.id)) {
                                    onTagToggled(existingTag)
                                }
                            } else {
                                onCustomTagAdded(newTagText)
                            }
                            newTagText = ""
                        }
                        showAddField = false
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
                IconButton(
                    onClick = {
                        newTagText = ""
                        showAddField = false
                    }
                ) {
                    Icon(Icons.Default.Close, contentDescription = "Cancel")
                }
            }
        } else {
            TextButton(
                onClick = { showAddField = true }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("Add new tag")
            }
        }
    }
}
