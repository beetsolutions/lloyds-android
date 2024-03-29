package com.beettechnologies.lloyds.home.data.model

data class CategoriesResponse(
    val categories: List<CategoryResponse>
)

data class CategoryResponse(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String,
)
