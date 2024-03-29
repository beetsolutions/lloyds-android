package com.beettechnologies.lloyds.home.data.repository

import com.beettechnologies.lloyds.common.data.model.ApiResponse
import com.beettechnologies.lloyds.common.data.model.Resource
import com.beettechnologies.lloyds.common.data.repository.NetworkResource
import com.beettechnologies.lloyds.home.data.api.MealApi
import com.beettechnologies.lloyds.home.data.mapper.CategoryMapper
import com.beettechnologies.lloyds.home.data.mapper.MealMapper
import com.beettechnologies.lloyds.home.data.model.CategoriesResponse
import com.beettechnologies.lloyds.home.data.model.MealsResponse
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import com.beettechnologies.lloyds.home.domain.model.MealModel
import com.beettechnologies.lloyds.home.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepositoryImpl @Inject constructor(
    private val mealApi: MealApi,
    private val categoryMapper: CategoryMapper,
    private val mealMapper: MealMapper) : MealRepository {

    override suspend fun getCategories(): Flow<Resource<List<CategoryModel>>> {
        return object : NetworkResource<List<CategoryModel>, CategoriesResponse>() {
            override suspend fun loadResults(item: CategoriesResponse?): Flow<List<CategoryModel>> {
                return flow {
                    emit(item?.let {
                        categoryMapper.map(it.categories)
                    } ?: emptyList())
                }
            }

            override suspend fun createNetworkRequest(): ApiResponse<CategoriesResponse> {
                return try {
                    ApiResponse.create(mealApi.categories())
                } catch (throwable: Throwable) {
                    val error = if (throwable is UnknownHostException) {
                        Throwable(message = "No internet connection available!")
                    } else throwable
                    ApiResponse.create(error)
                }
            }
        }.asFlow()
    }

    override suspend fun getMealsInACategory(category: String): Flow<Resource<List<MealModel>>> {
        return object : NetworkResource<List<MealModel>, MealsResponse>() {
            override suspend fun loadResults(item: MealsResponse?): Flow<List<MealModel>> {
                return flow {
                    emit(item?.let {
                        mealMapper.map(it.meals)
                    } ?: emptyList())
                }
            }

            override suspend fun createNetworkRequest(): ApiResponse<MealsResponse> {
                return try {
                    ApiResponse.create(mealApi.getMealsInACategory(category))
                } catch (throwable: Throwable) {
                    val error = if (throwable is UnknownHostException) {
                        Throwable(message = "No internet connection available!")
                    } else throwable
                    ApiResponse.create(error)
                }
            }
        }.asFlow()
    }
}
