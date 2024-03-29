package com.beettechnologies.lloyds.home.domain.mapper

import com.beettechnologies.lloyds.home.data.mapper.CategoryMapper
import com.beettechnologies.lloyds.home.data.model.CategoriesResponse
import com.beettechnologies.lloyds.home.data.model.CategoryResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class CategoryMapperTest {

    private val sut = CategoryMapper()

    @Test
    fun `map response , from categories, return list of CategoryModel`() {

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
        val categories = sut.map(response.categories)

        assertEquals(categories[0].id, "dummy-id")
        assertEquals(categories[0].name, "dummy-category")
        assertEquals(categories[0].imageUrl, "dummy-image")
        assertEquals(categories[0].description, "dummy-desc")
    }
}
