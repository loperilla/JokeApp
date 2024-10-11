package io.loperilla.jokeapp.domain.model.joke

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.model.joke
 * Created By Manuel Lopera on 10/10/24 at 18:57
 * All rights reserved 2024
 */
data class JokeFlags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)
