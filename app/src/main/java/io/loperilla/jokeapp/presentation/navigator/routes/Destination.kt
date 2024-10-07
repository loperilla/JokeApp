package io.loperilla.jokeapp.presentation.navigator.routes

import io.loperilla.jokeapp.domain.model.FormData
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
    data class Joke(
        val formData: FormData
    ): Destination
}
