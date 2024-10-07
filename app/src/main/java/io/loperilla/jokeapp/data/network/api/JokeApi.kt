package io.loperilla.jokeapp.data.network.api

import io.loperilla.jokeapp.data.network.model.CategoryApi
import io.loperilla.jokeapp.data.network.model.FlagApi
import io.loperilla.jokeapp.data.network.model.JokeLanguageApi
import io.loperilla.jokeapp.domain.model.DomainResult

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.api
 * Created By Manuel Lopera on 6/10/24 at 13:19
 * All rights reserved 2024
 */
interface JokeApi {
    suspend fun getLanguageList(): DomainResult<JokeLanguageApi>
    suspend fun getFlagsList(): DomainResult<FlagApi>
    suspend fun getCategoriesList(): DomainResult<CategoryApi>
}