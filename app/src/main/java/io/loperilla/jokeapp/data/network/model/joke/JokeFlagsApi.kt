package io.loperilla.jokeapp.data.network.model.joke

import io.loperilla.jokeapp.domain.model.joke.JokeFlags
import kotlinx.serialization.Serializable

@Serializable
data class JokeFlagsApi(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)

fun JokeFlagsApi.toDomain() = JokeFlags(
    explicit,
    nsfw,
    political,
    racist,
    religious,
    sexist
)