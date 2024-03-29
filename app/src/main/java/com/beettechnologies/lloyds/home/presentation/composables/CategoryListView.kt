package com.beettechnologies.lloyds.home.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beettechnologies.lloyds.app.navigation.Navigation
import com.beettechnologies.lloyds.app.navigation.NavigationPreviewParameterProvider
import com.beettechnologies.lloyds.home.domain.model.CategoryModel

@Composable
@Preview
fun CategoryListView(list: List<CategoryModel> = emptyList(), @PreviewParameter(
    NavigationPreviewParameterProvider::class) navigation: Navigation) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { model ->
            CategoryItemView(model, navigation)
        }
    }
}
