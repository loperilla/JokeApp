package io.loperilla.jokeapp.data.network.model.joke

import kotlinx.serialization.Serializable

@Serializable
data class JokeListApi(
    val amount: Int,
    val error: Boolean,
    val jokes: List<JokeModelApi>
)