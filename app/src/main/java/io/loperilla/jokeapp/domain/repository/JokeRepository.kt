package io.loperilla.jokeapp.domain.repository

import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.model.joke.JokeModel

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.repository
 * Created By Manuel Lopera on 10/10/24 at 18:50
 * All rights reserved 2024
 */
interface JokeRepository {
    suspend fun getJoke(formData: FormData): DomainResult<List<JokeModel>>
}