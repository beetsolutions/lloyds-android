package com.beettechnologies.lloyds.app.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.beettechnologies.lloyds.home.presentation.HomeView
import com.beettechnologies.lloyds.home.presentation.MealsView

@ExperimentalAnimationApi
@Composable
fun NavigationHost(navController: NavHostController, navigation: Navigation) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeView(navigation = navigation)
        }
        composable(route = Screen.Meals.route) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category")
            MealsView(navigation = navigation, category = category)
        }
    }
}
