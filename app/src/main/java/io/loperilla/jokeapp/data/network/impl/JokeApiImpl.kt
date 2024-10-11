package io.loperilla.jokeapp.data.network.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.CategoryApi
import io.loperilla.jokeapp.data.network.model.FlagApi
import io.loperilla.jokeapp.data.network.model.JokeLanguageApi
import io.loperilla.jokeapp.data.network.model.joke.JokeModelApi
import io.loperilla.jokeapp.data.network.utils.CATEGORIES
import io.loperilla.jokeapp.data.network.utils.FLAG
import io.loperilla.jokeapp.data.network.utils.JOKE
import io.loperilla.jokeapp.data.network.utils.JOKE_AMOUNT
import io.loperilla.jokeapp.data.network.utils.JOKE_FLAG
import io.loperilla.jokeapp.data.network.utils.JOKE_LANG
import io.loperilla.jokeapp.data.network.utils.LANGUAGES
import io.loperilla.jokeapp.data.network.utils.baseUrl
import io.loperilla.jokeapp.data.network.utils.processResponse
import io.loperilla.jokeapp.domain.model.FormData
import kotlinx.serialization.json.Json

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.impl
 * Created By Manuel Lopera on 6/10/24 at 13:25
 * All rights reserved 2024
 */
class JokeApiImpl(
    private val httpClient: HttpClient,
    private val json: Json
): JokeApi {
    override suspend fun getLanguageList(): ApiResult<JokeLanguageApi> {
        return processResponse(json) {
            httpClient.get {
                url("${baseUrl}$LANGUAGES")
            }
        }
    }

    override suspend fun getFlagsList(): ApiResult<FlagApi> {
        return processResponse(json) {
            httpClient.get {
                url("${baseUrl}$FLAG")
            }
        }
    }

    override suspend fun getCategoriesList(): ApiResult<CategoryApi> {
        return processResponse(json) {
            httpClient.get {
                url("${baseUrl}$CATEGORIES")
            }
        }
    }

    override suspend fun getJokeFromData(formData: FormData): ApiResult<JokeModelApi> {
        return processResponse(json) {
            httpClient.get {
                url {
                    val category = formData.categories
                    it.host = "$baseUrl$JOKE/$category"
                    it.parameters.apply {
                        append(JOKE_LANG, formData.language)
                        append(JOKE_FLAG, formData.flags)
                    }
                }
                url("${baseUrl}$baseUrl")
            }
        }
    }

    override suspend fun getJokeFromDataList(formData: FormData): ApiResult<List<JokeModelApi>> {
        return processResponse(json) {
            httpClient.get {
                url {
                    val category = formData.categories
                    it.host = "$baseUrl$JOKE/$category"
                    it.parameters.apply {
                        append(JOKE_LANG, formData.language)
                        append(JOKE_FLAG, formData.flags)
                        append(JOKE_AMOUNT, formData.amount.toString())
                    }
                }
                url("${baseUrl}$baseUrl")
            }
        }
    }
}