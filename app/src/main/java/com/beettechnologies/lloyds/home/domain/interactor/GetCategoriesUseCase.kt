package com.beettechnologies.lloyds.home.domain.interactor

import com.beettechnologies.lloyds.common.data.model.Resource
import com.beettechnologies.lloyds.common.interactor.BaseUseCase
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import com.beettechnologies.lloyds.home.domain.repository.MealRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoriesUseCase @Inject constructor(private val repository: MealRepository) :
    BaseUseCase<Unit, Flow<Resource<List<CategoryModel>>>>() {

    override suspend fun buildUseCase(params: Unit): Flow<Resource<List<CategoryModel>>> {
        return repository.getCategories()
    }
}
