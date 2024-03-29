package com.beettechnologies.lloyds.home.domain.repository

import com.beettechnologies.lloyds.common.data.model.Resource
import com.beettechnologies.lloyds.common.data.model.Status
import com.beettechnologies.lloyds.home.data.api.MealApi
import com.beettechnologies.lloyds.home.data.mapper.CategoryMapper
import com.beettechnologies.lloyds.home.data.mapper.MealMapper
import com.beettechnologies.lloyds.home.data.model.CategoriesResponse
import com.beettechnologies.lloyds.home.data.model.CategoryResponse
import com.beettechnologies.lloyds.home.data.repository.MealRepositoryImpl
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

class MealRepositoryTest {

    private val mealApi = mockk<MealApi>()
    private val sut: MealRepository = MealRepositoryImpl(mealApi, CategoryMapper(), MealMapper())

    @Test
    fun `get categories , server error, return Error`() = runTest {

        val response = "{\n" +
                "  \"detail\": \"Oops! Something happened.\"\n" +
                "}"

        coEvery {
            mealApi.categories()
        } returns Response.error(402, response.toResponseBody("application/json".toMediaTypeOrNull()))

        val actual = mutableListOf<Resource<List<CategoryModel>>>()

        sut.getCategories()
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, "Oops! Something happened.")
        assertEquals(actual[1].status, Status.ERROR)
        assertEquals(actual[1].data, null)
    }

    @Test
    fun `get categories with no internet , return Error`() = runTest {

        coEvery { mealApi.categories() } throws UnknownHostException()

        val actual = mutableListOf<Resource<List<CategoryModel>>>()

        sut.getCategories()
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, "No internet connection available!")
        assertEquals(actual[1].status, Status.ERROR)
        assertEquals(actual[1].data, null)
    }

    @Test
    fun `get categories , return Success`() = runTest {

        val response = CategoriesResponse(
            categories = listOf(
                CategoryResponse(
                    "dummy-id",
                    "dummy-category",
                    "dummy-image",
                    "dummy-desc"
                )
            )
        )

        coEvery { mealApi.categories() } returns Response.success(response)

        val actual = mutableListOf<Resource<List<CategoryModel>>>()

        sut.getCategories()
            .take(2)
            .collect {
                actual.add(it)
            }

        assertEquals(actual[0].message, null)
        assertEquals(actual[0].status, Status.LOADING)
        assertEquals(actual[0].data, null)

        assertEquals(actual[1].message, null)
        assertEquals(actual[1].status, Status.SUCCESS)
        assertEquals(actual[1].data?.get(0)?.id, "dummy-id")
        assertEquals(actual[1].data?.get(0)?.name, "dummy-category")
        assertEquals(actual[1].data?.get(0)?.imageUrl, "dummy-image")
        assertEquals(actual[1].data?.get(0)?.description, "dummy-desc")
    }
}
