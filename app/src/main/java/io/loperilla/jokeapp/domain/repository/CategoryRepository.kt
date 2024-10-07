package io.loperilla.jokeapp.domain.repository

import io.loperilla.jokeapp.domain.model.Category

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.repository
 * Created By Manuel Lopera on 6/10/24 at 16:48
 * All rights reserved 2024
 */
interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}