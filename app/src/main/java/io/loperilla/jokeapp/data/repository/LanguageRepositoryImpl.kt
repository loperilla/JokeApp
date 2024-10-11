package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.data.local.dao.LanguageDao
import io.loperilla.jokeapp.data.local.entity.LanguageEntity
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.JokeLanguageApi
import io.loperilla.jokeapp.data.network.model.NetworkLanguage
import io.loperilla.jokeapp.data.network.model.toDomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Language
import io.loperilla.jokeapp.domain.repository.LanguageRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.repository
 * Created By Manuel Lopera on 6/10/24 at 13:33
 * All rights reserved 2024
 */
class LanguageRepositoryImpl(
    private val api: JokeApi,
    private val dao: LanguageDao
) : LanguageRepository {
    override suspend fun getLanguages(): DomainResult<List<Language>> {
        val localLanguages = dao.getLanguages()
        return if (localLanguages.isNotEmpty()) {
            DomainResult.Success(localLanguages.map { Language(it.code, it.name) })
        } else {
            getNetworkLanguages()
        }
    }

    private suspend fun getNetworkLanguages(): DomainResult<List<Language>> {
        return when (val result = api.getLanguageList()) {
            is ApiResult.Error -> DomainResult.Error(result.error.toDomainError())
            is ApiResult.Success -> {
                val returnList = buildReturnList(result.data).map {
                    val domainLanguage = Language(
                        code = it.code,
                        name = it.name
                    )
                    dao.insert(
                        LanguageEntity(
                            code = it.code,
                            name = it.name
                        )
                    )
                    domainLanguage
                }
                DomainResult.Success(returnList)
            }
        }
    }

    private fun buildReturnList(networkResponse: JokeLanguageApi): List<NetworkLanguage> {
        val jokeLanguages = networkResponse.jokeLanguages
        val possibleLanguages = networkResponse.possibleLanguages

        return possibleLanguages.filter { it.code in jokeLanguages }
    }
}