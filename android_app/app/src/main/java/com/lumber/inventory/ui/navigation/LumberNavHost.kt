package com.lumber.inventory.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.lumber.inventory.ui.screens.settings.SettingsScreen
import com.lumber.inventory.ui.screens.tags.TagsScreen

/**
 * Main navigation host for the app.
 */
@Composable
fun LumberNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Inventory.route,
        modifier = modifier
    ) {
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
                onLumberAdded = { navController.popBackStack() }
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
