package io.loperilla.jokeapp.presentation.navigator

import androidx.navigation.NavOptionsBuilder
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import io.loperilla.jokeapp.presentation.navigator.routes.NavigationAction
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.navigator
 * Created By Manuel Lopera on 4/10/24 at 17:04
 * All rights reserved 2024
 */

interface Navigator {
    var startDestination: Destination
    val navigationActions: Flow<NavigationAction>

    fun setUpStartDestination(
        startDestination: Destination
    )

    suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit = {}
    )

    suspend fun navigateUp()
}

class DefaultNavigator : Navigator {
    override var startDestination: Destination = Destination.Welcome
    private val _navigationActions = Channel<NavigationAction>()
    override val navigationActions = _navigationActions.receiveAsFlow()

    override fun setUpStartDestination(startDestination: Destination) {
        this.startDestination = startDestination
    }

    override suspend fun navigate(
        destination: Destination,
        navOptions: NavOptionsBuilder.() -> Unit
    ) {
        _navigationActions.send(
            NavigationAction.Navigate(
                route = destination,
                navOptions = navOptions
            )
        )
    }

    override suspend fun navigateUp() {
        _navigationActions.send(NavigationAction.NavigateUp)
    }
}