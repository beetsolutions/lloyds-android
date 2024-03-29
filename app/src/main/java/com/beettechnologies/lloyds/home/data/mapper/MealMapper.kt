package com.beettechnologies.lloyds.home.data.mapper

import com.beettechnologies.lloyds.home.data.model.MealResponse
import com.beettechnologies.lloyds.home.domain.model.MealModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealMapper @Inject constructor() {

    fun map(response: List<MealResponse>): List<MealModel> {
        return response.map {
            MealModel(
                id = it.idMeal,
                name = it.strMeal,
                imageUrl = it.strMealThumb,
            )
        }
    }
}
