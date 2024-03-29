package com.beettechnologies.lloyds.home.domain.interactor

import com.beettechnologies.lloyds.common.data.model.Resource
import com.beettechnologies.lloyds.common.interactor.BaseUseCase
import com.beettechnologies.lloyds.home.domain.model.MealModel
import com.beettechnologies.lloyds.home.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMealsInACategoryUseCase @Inject constructor(private val repository: MealRepository) :
    BaseUseCase<GetMealsInACategoryUseCase.Params, Flow<Resource<List<MealModel>>>>() {

    override suspend fun buildUseCase(params: Params): Flow<Resource<List<MealModel>>> = repository.getMealsInACategory(params.category)

    data class Params(val category: String)
}
