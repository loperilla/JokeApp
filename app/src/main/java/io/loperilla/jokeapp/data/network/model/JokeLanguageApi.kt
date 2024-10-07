package io.loperilla.jokeapp.data.network.model

import kotlinx.serialization.Serializable

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.model
 * Created By Manuel Lopera on 6/10/24 at 13:20
 * All rights reserved 2024
 */
@Serializable
data class JokeLanguageApi(
    val jokeLanguages: List<String>,
    val possibleLanguages: List<NetworkLanguage>
)

@Serializable
data class NetworkLanguage(
    val code: String,
    val name: String
)
