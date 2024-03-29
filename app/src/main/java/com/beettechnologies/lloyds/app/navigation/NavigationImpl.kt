package com.beettechnologies.lloyds.app.navigation

import androidx.navigation.NavController
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationImpl @Inject constructor() : Navigation {

    private lateinit var navController: NavController

    override fun setController(controller: NavController) {
        navController = controller
    }

    override fun navigateToHome() {
        navController.navigate(Screen.Home.route) {
           launchSingleTop = true
        }
    }

    override fun navigateToMeals(category: String) {
        navController.navigate("meals/${category}") {
            popUpTo(Screen.Home.route) { saveState = true }
        }
    }

    override fun navigateBack() {
        navController.popBackStack()
    }
}
