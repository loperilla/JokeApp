package io.loperilla.jokeapp.domain.repository

import io.loperilla.jokeapp.domain.model.Language

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.repository
 * Created By Manuel Lopera on 6/10/24 at 13:31
 * All rights reserved 2024
 */
interface LanguageRepository {
    suspend fun getLanguages(): List<Language>
}