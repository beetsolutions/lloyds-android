package com.beettechnologies.lloyds.app.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Meals : Screen("meals/{category}")
}
