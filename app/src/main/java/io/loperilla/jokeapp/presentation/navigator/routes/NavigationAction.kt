package io.loperilla.jokeapp.presentation.navigator.routes

import androidx.navigation.NavOptionsBuilder

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.navigator.routes
 * Created By Manuel Lopera on 4/10/24 at 17:05
 * All rights reserved 2024
 */
sealed interface NavigationAction {
    data object NavigateUp : NavigationAction
    data class Navigate(
        val route: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ) : NavigationAction
}