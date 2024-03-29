package com.beettechnologies.lloyds.home.data.mapper

import com.beettechnologies.lloyds.home.data.model.CategoryResponse
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryMapper @Inject constructor() {

    fun map(response: List<CategoryResponse>): List<CategoryModel> {
        return response.map {
            CategoryModel(
                id = it.idCategory,
                name = it.strCategory,
                imageUrl = it.strCategoryThumb,
                description = it.strCategoryDescription
            )
        }
    }
}
