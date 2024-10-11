package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.repository.CategoryRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 6/10/24 at 16:51
 * All rights reserved 2024
 */
class GetCategoryListUseCase(
    private val repository: CategoryRepository
) {

    suspend operator fun invoke(): DomainResult<List<Category>> = repository.getCategories()
}