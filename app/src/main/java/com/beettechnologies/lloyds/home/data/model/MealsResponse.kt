package com.beettechnologies.lloyds.home.data.model

class MealsResponse(
    val meals: List<MealResponse>
)

data class MealResponse(
    val idMeal: String,
    val strMealThumb: String,
    val strMeal: String,
)
