package io.loperilla.jokeapp.data.repository

import io.loperilla.jokeapp.data.local.dao.CategoryDao
import io.loperilla.jokeapp.data.local.entity.CategoryEntity
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.toDomainError
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
    private val api: JokeApi,
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun getCategories(): DomainResult<List<Category>> {
        val localCategories = dao.getCategories()
        return if (localCategories.isNotEmpty()) {
            DomainResult.Success(localCategories.map { Category(it.alias, it.resolvedName) })
        } else {
            getNetworkCategoriesList()
        }
    }

    private suspend fun getNetworkCategoriesList(): DomainResult<List<Category>> {
        return when (val result = api.getCategoriesList()) {
            is ApiResult.Error -> DomainResult.Error(result.error.toDomainError())
            is ApiResult.Success -> {
                val resultList = result.data.categoryAliases.map {
                    val category = Category(
                        it.alias,
                        it.resolved
                    )
                    dao.insertCategory(
                        CategoryEntity(
                            alias = it.alias,
                            resolvedName = it.resolved
                        )
                    )
                    category
                }
                DomainResult.Success(resultList)
            }
        }
    }
}