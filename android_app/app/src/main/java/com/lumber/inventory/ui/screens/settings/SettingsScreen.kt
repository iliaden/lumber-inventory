package com.lumber.inventory.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lumber.inventory.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onLocationsClick: () -> Unit,
    onTagsClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val serverUrl by viewModel.serverUrl.collectAsState()
    val connectionState by viewModel.connectionState.collectAsState()
    var editedUrl by remember(serverUrl) { mutableStateOf(serverUrl) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(connectionState) {
        when (connectionState) {
            is ConnectionState.Success -> {
                snackbarHostState.showSnackbar(
                    message = "Connected successfully!"
                )
            }
            is ConnectionState.Error -> {
                snackbarHostState.showSnackbar(
                    message = (connectionState as ConnectionState.Error).message
                )
            }
            else -> {}
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_settings)) },
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
            Text(
                text = "Server Connection",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = editedUrl,
                onValueChange = { editedUrl = it },
                label = { Text(stringResource(R.string.label_server_url)) },
                placeholder = { Text(stringResource(R.string.hint_server_url)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                leadingIcon = {
                    Icon(Icons.Default.Cloud, contentDescription = null)
                }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedButton(
                    onClick = { viewModel.testConnection(editedUrl) },
                    enabled = connectionState !is ConnectionState.Testing,
                    modifier = Modifier.weight(1f)
                ) {
                    if (connectionState is ConnectionState.Testing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(stringResource(R.string.btn_test_connection))
                }

                Button(
                    onClick = { viewModel.saveServerUrl(editedUrl) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.btn_save))
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "Data Management",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Card(
                onClick = onLocationsClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Place,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.title_locations),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Manage storage locations",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = null
                    )
                }
            }

            Card(
                onClick = onTagsClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Label,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.title_tags),
                            style = MaterialTheme.typography.titleSmall
                        )
                        Text(
                            text = "Manage inventory tags",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = null
                    )
                }
            }

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            Text(
                text = "About",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Lumber Inventory v1.0.0",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "A mobile app for managing your woodworking lumber inventory.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
