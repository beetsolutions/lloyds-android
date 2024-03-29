package com.beettechnologies.lloyds.home.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beettechnologies.lloyds.home.domain.model.MealModel

@Composable
@Preview
fun MealListView(list: List<MealModel> = emptyList()) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { model ->
            MealItemView(model)
        }
    }
}
