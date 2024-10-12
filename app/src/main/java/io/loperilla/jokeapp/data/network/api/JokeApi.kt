package io.loperilla.jokeapp.data.network.api

import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.CategoryApi
import io.loperilla.jokeapp.data.network.model.FlagApi
import io.loperilla.jokeapp.data.network.model.JokeLanguageApi
import io.loperilla.jokeapp.data.network.model.joke.JokeListApi
import io.loperilla.jokeapp.data.network.model.joke.JokeModelApi
import io.loperilla.jokeapp.domain.model.FormData

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.api
 * Created By Manuel Lopera on 6/10/24 at 13:19
 * All rights reserved 2024
 */
interface JokeApi {
    suspend fun getLanguageList(): ApiResult<JokeLanguageApi>
    suspend fun getFlagsList(): ApiResult<FlagApi>
    suspend fun getCategoriesList(): ApiResult<CategoryApi>
    suspend fun getJokeFromData(formData: FormData): ApiResult<JokeModelApi>
    suspend fun getJokeFromDataList(formData: FormData): ApiResult<JokeListApi>
}