package com.beettechnologies.lloyds.app.navigation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.navigation.NavController

interface Navigation {
    fun setController(controller: NavController)
    fun navigateToHome()
    fun navigateToMeals(category: String)
    fun navigateBack()
}

class NavigationPreviewParameterProvider : PreviewParameterProvider<Navigation> {
    override val values: Sequence<Navigation> = sequenceOf(dummyNavigation)
}

val dummyNavigation = object : Navigation {
    override fun setController(controller: NavController) {
    }

    override fun navigateToHome() {
    }

    override fun navigateToMeals(category: String) {
    }

    override fun navigateBack() {
    }
}