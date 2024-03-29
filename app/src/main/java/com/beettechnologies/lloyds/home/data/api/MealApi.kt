package com.beettechnologies.lloyds.home.data.api

import com.beettechnologies.lloyds.home.data.model.CategoriesResponse
import com.beettechnologies.lloyds.home.data.model.MealsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("/api/json/v1/1/categories.php")
    suspend fun categories(): Response<CategoriesResponse>

    @GET("/api/json/v1/1/filter.php")
    suspend fun getMealsInACategory(@Query("c") category: String): Response<MealsResponse>
}
