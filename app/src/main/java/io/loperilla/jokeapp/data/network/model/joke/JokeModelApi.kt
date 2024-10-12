package io.loperilla.jokeapp.data.network.model.joke

import io.loperilla.jokeapp.domain.model.joke.JokeModel
import kotlinx.serialization.Serializable

@Serializable
data class JokeModelApi(
    val category: String,
    val delivery: String,
    val flags: JokeFlagsApi,
    val id: Int,
    val lang: String,
    val safe: Boolean,
    val setup: String,
    val type: String
)

fun JokeModelApi.toDomain() = JokeModel(
    category = category,
    delivery = delivery,
    flags = flags.toDomain(),
    id = id,
    lang = lang,
    safe = safe,
    setup = setup,
    type = type
)