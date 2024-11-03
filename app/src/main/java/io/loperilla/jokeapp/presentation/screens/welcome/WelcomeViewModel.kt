package io.loperilla.jokeapp.presentation.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
class WelcomeViewModel(
    private val navigator: Navigator
) : ViewModel() {

    fun onEvent(event: WelcomeEvent) = viewModelScope.launch {
        when (event) {
            WelcomeEvent.NavigateToJokeForm -> navigator.navigate(
                Destination.Form
            )
        }
    }
}
