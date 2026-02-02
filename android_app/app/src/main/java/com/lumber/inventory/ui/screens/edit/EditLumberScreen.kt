package com.lumber.inventory.ui.screens.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lumber.inventory.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditLumberScreen(
    lumberId: Int,
    onNavigateBack: () -> Unit,
    onLumberUpdated: () -> Unit,
    onLumberDeleted: () -> Unit,
    viewModel: EditLumberViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showDeleteDialog by remember { mutableStateOf(false) }
    
    LaunchedEffect(lumberId) {
        viewModel.loadLumber(lumberId)
    }
    
    LaunchedEffect(uiState) {
        when (uiState) {
            is EditLumberUiState.Updated -> onLumberUpdated()
            is EditLumberUiState.Deleted -> onLumberDeleted()
            is EditLumberUiState.Error -> {
                snackbarHostState.showSnackbar(
                    message = (uiState as EditLumberUiState.Error).message
                )
            }
            else -> {}
        }
    }
    
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.dialog_delete_title)) },
            text = { Text(stringResource(R.string.dialog_delete_message)) },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        viewModel.deleteLumber()
                    }
                ) {
                    Text(
                        stringResource(R.string.dialog_delete_confirm),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_edit_lumber)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = stringResource(R.string.cd_delete_lumber),
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
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        when (uiState) {
            is EditLumberUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = formState.species,
                        onValueChange = { viewModel.updateSpecies(it) },
                        label = { Text(stringResource(R.string.label_species)) },
                        isError = formState.speciesError != null,
                        supportingText = formState.speciesError?.let { { Text(it) } },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = formState.length,
                            onValueChange = { viewModel.updateLength(it) },
                            label = { Text(stringResource(R.string.label_length)) },
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
                    
                    OutlinedTextField(
                        value = formState.locationName,
                        onValueChange = { viewModel.updateLocationName(it) },
                        label = { Text(stringResource(R.string.label_location)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    
                    OutlinedTextField(
                        value = formState.tags,
                        onValueChange = { viewModel.updateTags(it) },
                        label = { Text(stringResource(R.string.label_tags)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
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
                            enabled = uiState !is EditLumberUiState.Saving,
                            modifier = Modifier.weight(1f)
                        ) {
                            if (uiState is EditLumberUiState.Saving) {
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
    }
}
