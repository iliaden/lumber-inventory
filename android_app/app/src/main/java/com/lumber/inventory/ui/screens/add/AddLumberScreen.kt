package com.lumber.inventory.ui.screens.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun AddLumberScreen(
    onNavigateBack: () -> Unit,
    onLumberAdded: () -> Unit,
    viewModel: AddLumberViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val formState by viewModel.formState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        when (uiState) {
            is AddLumberUiState.Success -> onLumberAdded()
            is AddLumberUiState.Error -> {
                snackbarHostState.showSnackbar(
                    message = (uiState as AddLumberUiState.Error).message
                )
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
                            Icons.Default.ArrowBack,
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
            OutlinedTextField(
                value = formState.species,
                onValueChange = { viewModel.updateSpecies(it) },
                label = { Text(stringResource(R.string.label_species)) },
                placeholder = { Text(stringResource(R.string.hint_species)) },
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

            OutlinedTextField(
                value = formState.locationName,
                onValueChange = { viewModel.updateLocationName(it) },
                label = { Text(stringResource(R.string.label_location)) },
                placeholder = { Text(stringResource(R.string.hint_new_location)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = formState.tags,
                onValueChange = { viewModel.updateTags(it) },
                label = { Text(stringResource(R.string.label_tags)) },
                placeholder = { Text("e.g., kiln-dried, project-table") },
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
