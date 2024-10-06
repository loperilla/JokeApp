package io.loperilla.jokeapp.presentation.navigator.routes

import kotlinx.serialization.Serializable

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.navigator.routes
 * Created By Manuel Lopera on 4/10/24 at 17:05
 * All rights reserved 2024
 */
sealed interface Destination {
    @Serializable
    data object Welcome: Destination
    @Serializable
    data object Form: Destination
    @Serializable
    data object Joke: Destination

}