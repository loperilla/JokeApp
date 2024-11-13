package io.loperilla.jokeapp.presentation.screens.jokeresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.model.getOrNull
import io.loperilla.jokeapp.domain.usecase.GetJokesUseCase
import io.loperilla.jokeapp.presentation.navigator.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
class JokeResultViewModel(
    formData: FormData,
    private val getJokesUseCase: GetJokesUseCase,
    private val navigator: Navigator
) : ViewModel() {
    private var _stateFlow: MutableStateFlow<JokeResultState> = MutableStateFlow(JokeResultState())
    val stateFlow: StateFlow<JokeResultState> = _stateFlow
        .onStart {
            _stateFlow.update {
                it.copy(
                    jokeModels = getJokesUseCase(formData).getOrNull() ?: emptyList(),
                    isLoading = false
                )

            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = JokeResultState()
        )

    fun onEvent(event: JokeResultEvent) = viewModelScope.launch {
        when (event) {
            is JokeResultEvent.BackToWelcome -> navigator.navigateUp()
        }
    }
}