package com.beettechnologies.lloyds.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import com.beettechnologies.lloyds.app.navigation.Navigation
import com.beettechnologies.lloyds.app.navigation.NavigationPreviewParameterProvider
import com.beettechnologies.lloyds.common.presentation.LoadingView
import com.beettechnologies.lloyds.common.presentation.OnLifecycleEvent
import com.beettechnologies.lloyds.common.presentation.ScreenView
import com.beettechnologies.lloyds.common.presentation.model.UiStateModel
import com.beettechnologies.lloyds.home.domain.model.MealModel
import com.beettechnologies.lloyds.home.presentation.composables.MealListView

@Composable
@Preview
fun MealsView(
    @PreviewParameter(NavigationPreviewParameterProvider::class) navigation: Navigation,
    category: String? = null
) {

    val viewModel = hiltViewModel<MealsViewModel>()
    val state by viewModel.uiState.collectAsState()

    ScreenView(title = category ?: "", showBackArrow = true, backAction = {
        navigation.navigateBack()
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 70.dp)
        ) {
            if (state is UiStateModel.Loading) {
                LoadingView(Modifier.align(Alignment.Center))
            }

            if (state is UiStateModel.Success) {
                val meals = (state as UiStateModel.Success).value as List<MealModel>
                MealListView(meals)
            }
        }
    }

    OnLifecycleEvent { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                category?.let { viewModel.getMealsByCategory(it) }
            }
            else -> {}
        }
    }
}

