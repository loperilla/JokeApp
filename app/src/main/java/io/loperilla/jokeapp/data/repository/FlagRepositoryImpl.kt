package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.data.local.dao.FlagDao
import io.loperilla.jokeapp.data.local.entity.FlagEntity
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.toDomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.repository.FlagRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.repository
 * Created By Manuel Lopera on 6/10/24 at 14:03
 * All rights reserved 2024
 */
class FlagRepositoryImpl(
    private val api: JokeApi,
    private val dao: FlagDao
) : FlagRepository {
    override suspend fun getFlag(): DomainResult<List<Flag>> {
        val localFlags = dao.getFlagsList()
        return if (localFlags.isNotEmpty()) {
            DomainResult.Success(localFlags.map { Flag(it.name) })
        } else {
            getNetworkFlag()
        }
    }

    private suspend fun getNetworkFlag(): DomainResult<List<Flag>> {
        return when (val result = api.getFlagsList()) {
            is ApiResult.Error -> DomainResult.Error(result.error.toDomainError())
            is ApiResult.Success -> {
                val returnList = result.data.flags.map {
                    val domainFlag = Flag(
                        it
                    )
                    dao.insert(
                        FlagEntity(
                            name = it
                        )
                    )
                    domainFlag
                }
                DomainResult.Success(returnList)
            }
        }
    }
}