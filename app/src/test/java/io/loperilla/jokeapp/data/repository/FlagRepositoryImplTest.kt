package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.data.local.dao.FlagDao
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.ApiResultError
import io.loperilla.jokeapp.domain.model.DomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.repository.FlagRepository
import io.loperilla.jokeapp.flagApi
import io.loperilla.jokeapp.flagEntity
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
 * Created By Manuel Lopera on 15/11/24 at 19:02
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class FlagRepositoryImplTest {

    private lateinit var flagRepository: FlagRepository
    private var api: JokeApi = mockk()
    private val dao: FlagDao = mockk()

    @BeforeEach
    fun setUp() {
        flagRepository = FlagRepositoryImpl(api, dao)
    }

    @Test
    fun `getFlag - not empty local data - return domain list`() = runTest {
        // GIVEN
        coEvery {
            dao.getFlagsList()
        } returns listOf(flagEntity)

        // ACTION
        val result = flagRepository.getFlag()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(Flag("dummy")))
    }

    @Test
    fun `getCategories - empty local data - return network domain list`() = runTest {
        // GIVEN
        coEvery {
            dao.getFlagsList()
        } returns emptyList()

        coEvery {
            api.getFlagsList()
        } returns ApiResult.Success((flagApi))

        coEvery {
            dao.insert(any())
        } returns Unit

        // ACTION
        val result = flagRepository.getFlag()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(Flag("dummy")))

        coVerify {
            dao.insert(any())
        }
    }

    @Test
    fun `getFlagsList - empty local data - error api - return error`() = runTest {
        // GIVEN
        coEvery {
            dao.getFlagsList()
        } returns emptyList()

        coEvery {
            api.getFlagsList()
        } returns ApiResult.Error(ApiResultError.NetworkError(404, "Not Found"))

        // ACTION
        val result = flagRepository.getFlag()

        // ASSERTION
        assertTrue(result is DomainResult.Error)
        assertTrue(result.error is DomainError.NetworkError)
    }

}