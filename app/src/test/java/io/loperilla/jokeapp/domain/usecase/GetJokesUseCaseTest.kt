package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.JokeRepository
import io.loperilla.jokeapp.formData
import io.loperilla.jokeapp.jokeDummy
import io.loperilla.jokeapp.unknownDomainError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 15/11/24 at 18:02
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class GetJokesUseCaseTest {
    private lateinit var getJokesUseCase: GetJokesUseCase
    private val repository: JokeRepository = mockk()

    @BeforeEach
    fun setUp() {
        getJokesUseCase = GetJokesUseCase(repository)
    }

    @Test
    fun `getJokesUseCase - OK`() = runTest {
        // GIVEN
        coEvery {
            getJokesUseCase.invoke(formData)
        } returns DomainResult.Success(listOf(jokeDummy))

        // ACTION
        val result = getJokesUseCase.invoke(formData)

        // ASSERTION
        assert(result is DomainResult.Success)
    }

    @Test
    fun `getJokesUseCase - Error`() = runTest {
        // GIVEN
        coEvery {
            getJokesUseCase.invoke(formData)
        } returns DomainResult.Error(unknownDomainError)

        // ACTION
        val result = getJokesUseCase.invoke(formData)

        // ASSERTION
        assert(result is DomainResult.Error)
    }
}