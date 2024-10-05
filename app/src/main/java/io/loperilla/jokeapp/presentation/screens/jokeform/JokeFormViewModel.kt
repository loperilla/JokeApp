package io.loperilla.jokeapp.presentation.screens.jokeform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import kotlinx.coroutines.launch

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
class JokeFormViewModel(
    private val navigator: Navigator
): ViewModel() {

    fun onEvent(event: JokeFormEvent) = viewModelScope.launch {
        when (event) {
            JokeFormEvent.NavigateToJokeResult -> navigator.navigate(
                Destination.Joke,
                navOptions = {
                    popUpTo(Destination.Form) {
                        inclusive = true
                    }
                }
            )
        }
    }
}