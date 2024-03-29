package com.beettechnologies.lloyds.home.presentation

import com.beettechnologies.lloyds.CoroutineTestRule
import com.beettechnologies.lloyds.common.data.model.Resource
import com.beettechnologies.lloyds.common.presentation.model.UiStateModel
import com.beettechnologies.lloyds.home.domain.interactor.GetCategoriesUseCase
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainCoroutineRule = CoroutineTestRule()

    private val categoriesUseCase = spyk(GetCategoriesUseCase(mockk()))
    private val sut = spyk(HomeViewModel(categoriesUseCase))

    @Test
    fun `get categories  , no internet, return Error`() = runTest {
        coEvery { categoriesUseCase(Unit) } returns flow { emit(Resource.error(ERROR_MESSAGE,null)) }

        sut.getCategories()
        advanceUntilIdle()

        val res = sut.uiState.value as UiStateModel.Error
        Assert.assertEquals(res.errorMessage , ERROR_MESSAGE)
        coVerify { categoriesUseCase(Unit) }
    }

    @Test
    fun `get categories , fetching with no errors, return Success`() = runTest {
        coEvery { categoriesUseCase(Unit) } returns flow { emit(Resource.success(CATEGORIES)) }
        sut.getCategories()
        advanceUntilIdle()

        val res = sut.uiState.value as UiStateModel.Success
        Assert.assertEquals(res.value, CATEGORIES)
        coVerify { categoriesUseCase(Unit) }
    }

    @Test
    fun `get categories , fetching, return Loading`() = runTest {
        coEvery { categoriesUseCase(Unit) } returns flow { emit(Resource.loading()) }
        sut.getCategories()
        advanceUntilIdle()

        val res = sut.uiState.value as UiStateModel.Loading
        Assert.assertEquals(res, UiStateModel.Loading)
        coVerify { categoriesUseCase(Unit) }
    }

    companion object {
        private const val ERROR_MESSAGE = "No internet available."
        private val CATEGORIES = listOf(CategoryModel("dummy-id", "dummy-category", "dummy-url", "dummy-desc"))
    }
}
