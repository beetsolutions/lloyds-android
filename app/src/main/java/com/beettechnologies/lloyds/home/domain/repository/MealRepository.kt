package com.beettechnologies.lloyds.home.domain.repository

import com.beettechnologies.lloyds.common.data.model.Resource
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import com.beettechnologies.lloyds.home.domain.model.MealModel
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getCategories(): Flow<Resource<List<CategoryModel>>>
    suspend fun getMealsInACategory(category: String): Flow<Resource<List<MealModel>>>
}
