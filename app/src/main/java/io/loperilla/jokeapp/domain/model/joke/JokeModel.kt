package io.loperilla.jokeapp.domain.model.joke

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.model
 * Created By Manuel Lopera on 10/10/24 at 18:50
 * All rights reserved 2024
 */
data class JokeModel(
    val id: Int,
    val category: String,
    val delivery: String,
    val error: Boolean,
    val flags: JokeFlags,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)
