package io.loperilla.jokeapp.data.network.model

import kotlinx.serialization.Serializable

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.model
 * Created By Manuel Lopera on 6/10/24 at 16:45
 * All rights reserved 2024
 */
@Serializable
data class CategoryApi(
    val categoryAliases: List<CategoryAliasesApi>,
)

@Serializable
data class CategoryAliasesApi(
    val alias: String,
    val resolved: String
)