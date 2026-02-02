package com.lumber.inventory.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.lumber.inventory.data.repository.SettingsRepository
import com.lumber.inventory.ui.navigation.LumberNavHost
import com.lumber.inventory.ui.navigation.Screen
import com.lumber.inventory.ui.theme.LumberInventoryTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main activity for the Lumber Inventory app.
 * Uses single-activity architecture with Jetpack Compose Navigation.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LumberInventoryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(settingsRepository)
                }
            }
        }
    }
}

@Composable
private fun MainContent(settingsRepository: SettingsRepository) {
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val isSetupCompleted = settingsRepository.isSetupCompleted()
        startDestination = if (isSetupCompleted) {
            Screen.Inventory.route
        } else {
            Screen.Setup.route
        }
    }

    if (startDestination != null) {
        val navController = rememberNavController()
        LumberNavHost(
            navController = navController,
            startDestination = startDestination!!
        )
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
