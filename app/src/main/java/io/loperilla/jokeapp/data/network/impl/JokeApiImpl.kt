package io.loperilla.jokeapp.data.network.impl

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.CategoryApi
import io.loperilla.jokeapp.data.network.model.FlagApi
import io.loperilla.jokeapp.data.network.model.JokeLanguageApi
import io.loperilla.jokeapp.data.network.utils.CATEGORIES
import io.loperilla.jokeapp.data.network.utils.FLAG
import io.loperilla.jokeapp.data.network.utils.LANGUAGES
import io.loperilla.jokeapp.data.network.utils.baseUrl
import io.loperilla.jokeapp.data.network.utils.processResponse
import io.loperilla.jokeapp.domain.model.DomainResult
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
    override suspend fun getLanguageList(): DomainResult<JokeLanguageApi> {
        return processResponse(json) {
            httpClient.get {
                url("${baseUrl}$LANGUAGES")
            }
        }
    }

    override suspend fun getFlagsList(): DomainResult<FlagApi> {
        return processResponse(json) {
            httpClient.get {
                url("${baseUrl}$FLAG")
            }
        }
    }

    override suspend fun getCategoriesList(): DomainResult<CategoryApi> {
        return processResponse(json) {
            httpClient.get {
                url("${baseUrl}$CATEGORIES")
            }
        }
    }
}