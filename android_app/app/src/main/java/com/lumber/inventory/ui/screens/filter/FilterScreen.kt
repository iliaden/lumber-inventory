package com.lumber.inventory.ui.screens.filter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lumber.inventory.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterScreen(
    onNavigateBack: () -> Unit,
    onFiltersApplied: () -> Unit
) {
    var species by remember { mutableStateOf("") }
    var minLength by remember { mutableStateOf("") }
    var maxLength by remember { mutableStateOf("") }
    var minThickness by remember { mutableStateOf("") }
    var maxThickness by remember { mutableStateOf("") }
    var selectedSurface by remember { mutableStateOf<Boolean?>(null) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_filter)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        species = ""
                        minLength = ""
                        maxLength = ""
                        minThickness = ""
                        maxThickness = ""
                        selectedSurface = null
                    }) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear filters",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Filter by Species",
                style = MaterialTheme.typography.titleSmall
            )
            
            OutlinedTextField(
                value = species,
                onValueChange = { species = it },
                label = { Text(stringResource(R.string.label_species)) },
                placeholder = { Text("e.g., Oak, Walnut") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            
            Text(
                text = "Filter by Surface",
                style = MaterialTheme.typography.titleSmall
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = selectedSurface == null,
                    onClick = { selectedSurface = null },
                    label = { Text("All") }
                )
                FilterChip(
                    selected = selectedSurface == false,
                    onClick = { selectedSurface = false },
                    label = { Text(stringResource(R.string.label_rough)) }
                )
                FilterChip(
                    selected = selectedSurface == true,
                    onClick = { selectedSurface = true },
                    label = { Text(stringResource(R.string.label_planed)) }
                )
            }
            
            Text(
                text = "Filter by Length",
                style = MaterialTheme.typography.titleSmall
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = minLength,
                    onValueChange = { minLength = it },
                    label = { Text("Min") },
                    placeholder = { Text("e.g., 24") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = maxLength,
                    onValueChange = { maxLength = it },
                    label = { Text("Max") },
                    placeholder = { Text("e.g., 96") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            
            Text(
                text = "Filter by Thickness",
                style = MaterialTheme.typography.titleSmall
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = minThickness,
                    onValueChange = { minThickness = it },
                    label = { Text("Min") },
                    placeholder = { Text("e.g., 3/4") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = maxThickness,
                    onValueChange = { maxThickness = it },
                    label = { Text("Max") },
                    placeholder = { Text("e.g., 2") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        species = ""
                        minLength = ""
                        maxLength = ""
                        minThickness = ""
                        maxThickness = ""
                        selectedSurface = null
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.btn_clear))
                }
                
                Button(
                    onClick = {
                        // TODO: Apply filters through shared ViewModel
                        onFiltersApplied()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.btn_apply))
                }
            }
        }
    }
}
