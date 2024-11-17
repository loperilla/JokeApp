package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.categoryEntity
import io.loperilla.jokeapp.data.local.dao.CategoryDao
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.CategoryApi
import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.DomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.CategoryRepository
import io.loperilla.jokeapp.miscellaneousCategory
import io.loperilla.jokeapp.miscellaneousCategoryAliasApi
import io.loperilla.jokeapp.networkApiError
import io.loperilla.jokeapp.programmingCategory
import io.loperilla.jokeapp.programmingCategoryAliasApi
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.repository
 * Created By Manuel Lopera on 15/11/24 at 18:17
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class CategoryRepositoryImplTest {

    private lateinit var categoryRepository: CategoryRepository
    private var api: JokeApi = mockk()
    private val dao: CategoryDao = mockk()

    @BeforeEach
    fun setUp() {
        categoryRepository = CategoryRepositoryImpl(api, dao)
    }

    @Test
    fun `getCategories - not empty local data - return domain list`() = runTest {
        // GIVEN
        coEvery {
            dao.getCategories()
        } returns listOf(categoryEntity)

        // ACTION
        val result = categoryRepository.getCategories()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(Category("dummy", "dummy")))
    }

    @Test
    fun `getCategories - empty local data - return networkdomain list`() = runTest {
        // GIVEN
        coEvery {
            dao.getCategories()
        } returns emptyList()

        coEvery {
            api.getCategoriesList()
        } returns ApiResult.Success(
            (CategoryApi(
                categoryAliases = listOf(
                    miscellaneousCategoryAliasApi,
                    programmingCategoryAliasApi
                )
            ))
        )

        coEvery {
            dao.insertCategory(any())
        } returns 1L

        // ACTION
        val result = categoryRepository.getCategories()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(miscellaneousCategory, programmingCategory))

        coVerify(exactly = 2){
            dao.insertCategory(any())
        }
    }

    @Test
    fun `getCategories - empty local data - error api - return error`() = runTest {
        // GIVEN
        coEvery {
            dao.getCategories()
        } returns emptyList()

        coEvery {
            api.getCategoriesList()
        } returns ApiResult.Error(networkApiError)

        // ACTION
        val result = categoryRepository.getCategories()

        // ASSERTION
        assertTrue(result is DomainResult.Error)
        assertTrue(result.error is DomainError.NetworkError)
    }

}