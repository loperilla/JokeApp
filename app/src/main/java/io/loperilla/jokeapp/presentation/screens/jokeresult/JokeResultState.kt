package io.loperilla.jokeapp.presentation.screens.jokeresult

import io.loperilla.jokeapp.domain.model.joke.JokeModel

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
data class JokeResultState(
    val isLoading: Boolean = false,
    val jokeModels: List<JokeModel> = emptyList()
)
