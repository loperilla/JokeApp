package io.loperilla.jokeapp.domain.model

import kotlinx.serialization.Serializable

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.model
 * Created By Manuel Lopera on 6/10/24 at 17:37
 * All rights reserved 2024
 */
@Serializable
data class FormData(
    val language: String,
    val categories: String,
    val flags: String,
    val amount: Int
)

fun List<Flag>.toForm(): String = this.joinToString(",") { it.name }