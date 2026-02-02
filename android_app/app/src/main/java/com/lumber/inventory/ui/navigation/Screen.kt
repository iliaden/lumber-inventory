package com.lumber.inventory.ui.navigation

/**
 * Defines navigation routes for the app.
 */
sealed class Screen(val route: String) {
    object Setup : Screen("setup")
    object Inventory : Screen("inventory")
    object AddLumber : Screen("add_lumber")
    object EditLumber : Screen("edit_lumber/{lumberId}") {
        fun createRoute(lumberId: Int) = "edit_lumber/$lumberId"
    }
    object Filter : Screen("filter")
    object Settings : Screen("settings")
    object Locations : Screen("locations")
    object Tags : Screen("tags")

    companion object {
        const val LUMBER_ID_ARG = "lumberId"
    }
}
