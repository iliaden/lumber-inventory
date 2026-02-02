package com.lumber.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lumber.inventory.ui.screens.add.AddLumberScreen
import com.lumber.inventory.ui.screens.edit.EditLumberScreen
import com.lumber.inventory.ui.screens.filter.FilterScreen
import com.lumber.inventory.ui.screens.inventory.InventoryScreen
import com.lumber.inventory.ui.screens.locations.LocationsScreen
import com.lumber.inventory.ui.screens.reekon.ReekonMeasurementScreen
import com.lumber.inventory.ui.screens.settings.SettingsScreen
import com.lumber.inventory.ui.screens.setup.SetupScreen
import com.lumber.inventory.ui.screens.tags.TagsScreen

/**
 * Main navigation host for the app.
 */
@Composable
fun LumberNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Setup.route) {
            SetupScreen(
                onSetupComplete = {
                    navController.navigate(Screen.Inventory.route) {
                        popUpTo(Screen.Setup.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Inventory.route) {
            InventoryScreen(
                onAddClick = { navController.navigate(Screen.AddLumber.route) },
                onEditClick = { lumberId ->
                    navController.navigate(Screen.EditLumber.createRoute(lumberId))
                },
                onFilterClick = { navController.navigate(Screen.Filter.route) },
                onSettingsClick = { navController.navigate(Screen.Settings.route) }
            )
        }

        composable(Screen.AddLumber.route) {
            AddLumberScreen(
                onNavigateBack = { navController.popBackStack() },
                onMeasureWithReekon = { navController.navigate(Screen.ReekonMeasurement.route) }
            )
        }

        composable(
            route = Screen.AddLumberWithMeasurements.route,
            arguments = listOf(
                navArgument(Screen.LENGTH_ARG) { type = NavType.FloatType },
                navArgument(Screen.WIDTH_ARG) { type = NavType.FloatType },
                navArgument(Screen.THICKNESS_ARG) { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val length = backStackEntry.arguments?.getFloat(Screen.LENGTH_ARG)?.toDouble() ?: 0.0
            val width = backStackEntry.arguments?.getFloat(Screen.WIDTH_ARG)?.toDouble() ?: 0.0
            val thickness = backStackEntry.arguments?.getFloat(Screen.THICKNESS_ARG)?.toDouble() ?: 0.0
            AddLumberScreen(
                onNavigateBack = { navController.popBackStack() },
                onMeasureWithReekon = { navController.navigate(Screen.ReekonMeasurement.route) },
                initialLength = length,
                initialWidth = width,
                initialThickness = thickness
            )
        }

        composable(Screen.ReekonMeasurement.route) {
            ReekonMeasurementScreen(
                onNavigateBack = { navController.popBackStack() },
                onMeasurementsComplete = { length, width, thickness ->
                    navController.navigate(
                        Screen.AddLumberWithMeasurements.createRoute(length, width, thickness)
                    ) {
                        popUpTo(Screen.AddLumber.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Screen.EditLumber.route,
            arguments = listOf(
                navArgument(Screen.LUMBER_ID_ARG) { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val lumberId = backStackEntry.arguments?.getInt(Screen.LUMBER_ID_ARG) ?: return@composable
            EditLumberScreen(
                lumberId = lumberId,
                onNavigateBack = { navController.popBackStack() },
                onLumberUpdated = { navController.popBackStack() },
                onLumberDeleted = { navController.popBackStack() }
            )
        }

        composable(Screen.Filter.route) {
            FilterScreen(
                onNavigateBack = { navController.popBackStack() },
                onFiltersApplied = { navController.popBackStack() }
            )
        }

        composable(Screen.Settings.route) {
            SettingsScreen(
                onNavigateBack = { navController.popBackStack() },
                onLocationsClick = { navController.navigate(Screen.Locations.route) },
                onTagsClick = { navController.navigate(Screen.Tags.route) }
            )
        }

        composable(Screen.Locations.route) {
            LocationsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Tags.route) {
            TagsScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
