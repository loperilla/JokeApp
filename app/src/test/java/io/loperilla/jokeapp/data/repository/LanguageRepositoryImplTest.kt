package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.data.local.dao.LanguageDao
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.domain.model.DomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Language
import io.loperilla.jokeapp.domain.repository.LanguageRepository
import io.loperilla.jokeapp.languageApi
import io.loperilla.jokeapp.languageEntity
import io.loperilla.jokeapp.networkApiError
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
 * Created By Manuel Lopera on 15/11/24 at 19:12
 * All rights reserved 2024
 */
@ExtendWith(MainCoroutineExtension::class)
class LanguageRepositoryImplTest {

    private lateinit var flagRepository: LanguageRepository
    private var api: JokeApi = mockk()
    private val dao: LanguageDao = mockk()

    @BeforeEach
    fun setUp() {
        flagRepository = LanguageRepositoryImpl(api, dao)
    }

    @Test
    fun `getLanguages - not empty local data - return domain list`() = runTest {
        // GIVEN
        coEvery {
            dao.getLanguages()
        } returns listOf(languageEntity)

        // ACTION
        val result = flagRepository.getLanguages()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(Language("dummy", "dummy")))
    }

    @Test
    fun `getLanguages - empty local data - return networkdomain list`() = runTest {
        // GIVEN
        coEvery {
            dao.getLanguages()
        } returns emptyList()

        coEvery {
            api.getLanguageList()
        } returns ApiResult.Success((languageApi))

        coEvery {
            dao.insert(any())
        } returns Unit

        // ACTION
        val result = flagRepository.getLanguages()

        // ASSERTION
        assertTrue(result is DomainResult.Success)
        assertEquals(result.data, listOf(Language("dummy", "dummy")))

        coVerify {
            dao.insert(any())
        }
    }

    @Test
    fun `getLanguages - empty local data - error api - return error`() = runTest {
        // GIVEN
        coEvery {
            dao.getLanguages()
        } returns emptyList()

        coEvery {
            api.getLanguageList()
        } returns ApiResult.Error(networkApiError)

        // ACTION
        val result = flagRepository.getLanguages()

        // ASSERTION
        assertTrue(result is DomainResult.Error)
        assertTrue(result.error is DomainError.NetworkError)
    }

}