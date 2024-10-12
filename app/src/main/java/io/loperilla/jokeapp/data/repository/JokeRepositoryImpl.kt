package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.joke.toDomain
import io.loperilla.jokeapp.data.network.model.toDomainError
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.model.joke.JokeModel
import io.loperilla.jokeapp.domain.repository.JokeRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.repository
 * Created By Manuel Lopera on 10/10/24 at 18:51
 * All rights reserved 2024
 */
class JokeRepositoryImpl(
    private val api: JokeApi,
): JokeRepository {
    override suspend fun getJoke(formData: FormData): DomainResult<List<JokeModel>> {
        return if (formData.amount == 1) {
            getSingleJoke(formData)
        } else {
            getListOfJokes(formData)
        }
    }

    private suspend fun getSingleJoke(formData: FormData): DomainResult<List<JokeModel>> {
        return when(val result = api.getJokeFromData(formData)) {
            is ApiResult.Error -> DomainResult.Error(result.error.toDomainError())
            is ApiResult.Success -> {
                DomainResult.Success(listOf(result.data.toDomain()))
            }
        }
    }

    private suspend fun getListOfJokes(formData: FormData): DomainResult<List<JokeModel>> {
        return when(val result = api.getJokeFromDataList(formData)) {
            is ApiResult.Error -> DomainResult.Error(result.error.toDomainError())
            is ApiResult.Success -> {
                DomainResult.Success(
                    result.data.jokes.map { it.toDomain() }
                )
            }
        }
    }
}
