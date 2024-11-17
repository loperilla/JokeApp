package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.LanguageRepository
import io.loperilla.jokeapp.languageEN
import io.loperilla.jokeapp.languageES
import io.loperilla.jokeapp.unknownDomainError
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 12/10/24 at 13:55
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class GetLanguageUseCaseTest {

    private lateinit var getLanguageUseCase: GetLanguageUseCase
    private var languageRepository: LanguageRepository = mockk()

    @BeforeEach
    fun setUp() {
        getLanguageUseCase = GetLanguageUseCase(languageRepository)
    }

    @Test
    fun `useCase invoke - OK`() = runTest {
        // GIVEN
        coEvery {
            getLanguageUseCase.invoke()
        } returns DomainResult.Success(listOf(languageEN, languageES))

        // ACTION
        val result = getLanguageUseCase.invoke()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
    }

    @Test
    fun `useCase invoke - Error`() = runTest {
        // GIVEN
        coEvery {
            getLanguageUseCase.invoke()
        } returns DomainResult.Error(unknownDomainError)

        // ACTION
        val result = getLanguageUseCase.invoke()

        // ASSERTION
        assertTrue(result is DomainResult.Error)
    }
}