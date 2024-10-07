package io.loperilla.jokeapp.presentation.screens.jokeresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.loperilla.jokeapp.domain.model.FormData
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
class JokeResultViewModel(
    formData: FormData,
    private val navigator: Navigator
) : ViewModel() {
    private var _stateFlow: MutableStateFlow<FormData> = MutableStateFlow(formData)
    val stateFlow: StateFlow<FormData> = _stateFlow.asStateFlow()
    fun onEvent(event: JokeResultEvent) = viewModelScope.launch {
        when (event) {
            is JokeResultEvent.BackToWelcome -> navigator.navigateUp()
        }
    }
}