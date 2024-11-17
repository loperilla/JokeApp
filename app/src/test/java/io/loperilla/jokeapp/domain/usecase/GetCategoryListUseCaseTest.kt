package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.CategoryRepository
import io.loperilla.jokeapp.miscellaneousCategory
import io.loperilla.jokeapp.programmingCategory
import io.loperilla.jokeapp.unknownDomainError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertTrue

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 15/11/24 at 17:40
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class GetCategoryListUseCaseTest {
    private lateinit var useCase: GetCategoryListUseCase
    private var repository: CategoryRepository = mockk()

    @BeforeEach
    fun setUp() {
        useCase = GetCategoryListUseCase(repository)
    }


    @Test
    fun `useCase invoke - OK`() = runTest {
        // GIVEN
        coEvery {
            useCase.invoke()
        } returns DomainResult.Success(listOf(programmingCategory, miscellaneousCategory))

        // ACTION
        val result = useCase.invoke()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
    }

    @Test
    fun `useCase invoke - Error`() = runTest {
        // GIVEN
        coEvery {
            useCase.invoke()
        } returns DomainResult.Error(unknownDomainError)

        // ACTION
        val result = useCase.invoke()

        // ASSERTION
        assertTrue(result is DomainResult.Error)
    }
}