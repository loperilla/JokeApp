package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.CategoryRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.repository
 * Created By Manuel Lopera on 6/10/24 at 16:49
 * All rights reserved 2024
 */
class CategoryRepositoryImpl(
    private val api: JokeApi
): CategoryRepository {

    override suspend fun getCategories(): List<Category> {
        val response = api.getCategoriesList()
        return if (response is DomainResult.Success) {
            response.data.categoryAliases.map {
                Category(
                    it.alias,
                    it.resolved
                )
            }
        } else {
            emptyList()
        }
    }
}