package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.ApiResultError
import io.loperilla.jokeapp.domain.model.DomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.repository.JokeRepository
import io.loperilla.jokeapp.flagApi
import io.loperilla.jokeapp.formData
import io.loperilla.jokeapp.formDataWithAmountTwo
import io.loperilla.jokeapp.jokeDummy
import io.loperilla.jokeapp.jokeListApi
import io.loperilla.jokeapp.jokeModelApi
import io.loperilla.jokeapp.networkApiError
import io.loperilla.jokeapp.networkDomainError
import io.loperilla.jokeapp.unknownDomainError
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
 * Created By Manuel Lopera on 15/11/24 at 19:21
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class JokeRepositoryImplTest {

    private lateinit var jokeRepository: JokeRepository
    private var api: JokeApi = mockk()

    @BeforeEach
    fun setUp() {
        jokeRepository = JokeRepositoryImpl(api)
    }

    @Test
    fun `getJoke - request amount 0 - return error`() = runTest {
        // GIVEN
        val badFormData = formData.copy(amount = 0)

        // ACTION
        val result = jokeRepository.getJoke(badFormData)

        // ASSERTION
        assertTrue(result is DomainResult.Error)
    }

    @Test
    fun `getJoke - request amount 1 - return domain list`() = runTest {
        // GIVEN
        coEvery {
            api.getJokeFromData(formData)
        } returns ApiResult.Success((jokeModelApi))

        // ACTION
        val result = jokeRepository.getJoke(formData)

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(jokeDummy))
    }

    @Test
    fun `getJoke - request amount 1 - return error`() = runTest {
        // GIVEN
        coEvery {
            api.getJokeFromData(formData)
        } returns ApiResult.Error(networkApiError)

        // ACTION
        val result = jokeRepository.getJoke(formData)

        // ASSERTION
        assertTrue(result is DomainResult.Error)
        assertEquals(result.error, networkDomainError)
    }

    @Test
    fun `getJoke - request amount 2 - return domain list`() = runTest {
        // GIVEN
        coEvery {
            api.getJokeFromDataList(formDataWithAmountTwo)
        } returns ApiResult.Success(jokeListApi)

        // ACTION
        val result = jokeRepository.getJoke(formDataWithAmountTwo)

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(jokeDummy))
    }

    @Test
    fun `getJoke - request amount 2 - return error`() = runTest {
        // GIVEN
        coEvery {
            api.getJokeFromDataList(formDataWithAmountTwo)
        } returns ApiResult.Error(networkApiError)

        // ACTION
        val result = jokeRepository.getJoke(formDataWithAmountTwo)

        // ASSERTION
        assertTrue(result is DomainResult.Error)
        assertEquals(result.error, networkDomainError)
    }

}