package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.model.DomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.FlagRepository
import io.loperilla.jokeapp.nsfwFlag
import io.loperilla.jokeapp.politicalFlag
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 15/11/24 at 17:55
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class GetFlagsUseCaseTest {

    private lateinit var getFlagsUseCase: GetFlagsUseCase
    private val flagRepository = mockk<FlagRepository>()

    @BeforeEach
    fun setUp() {
        getFlagsUseCase = GetFlagsUseCase(flagRepository)
    }

    @Test
    fun `getFlagsUseCase - OK`() = runTest {
        // GIVEN
        coEvery {
            getFlagsUseCase.invoke()
        } returns DomainResult.Success(listOf(nsfwFlag, politicalFlag))

        // ACTION
        val result = getFlagsUseCase.invoke()

        // ASSERTION
        assert(result is DomainResult.Success)
    }

    @Test
    fun `getFlagsUseCase - Error`() = runTest {
        // GIVEN
        coEvery {
            getFlagsUseCase.invoke()
        } returns DomainResult.Error(DomainError.UnknownError(Throwable()))

        // ACTION
        val result = getFlagsUseCase.invoke()

        // ASSERTION
        assert(result is DomainResult.Error)
    }


}