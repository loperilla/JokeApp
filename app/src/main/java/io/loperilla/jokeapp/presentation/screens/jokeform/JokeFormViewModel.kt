package io.loperilla.jokeapp.presentation.screens.jokeform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.usecase.GetCategoryListUseCase
import io.loperilla.jokeapp.domain.usecase.GetFlagsUseCase
import io.loperilla.jokeapp.domain.usecase.GetLanguageUseCase
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
class JokeFormViewModel(
    private val navigator: Navigator,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val getFlagsUseCase: GetFlagsUseCase
): ViewModel() {
    private var _stateFlow: MutableStateFlow<JokeFormState> = MutableStateFlow(JokeFormState())
    val stateFlow: StateFlow<JokeFormState> = _stateFlow
        .onStart {
            withContext(Dispatchers.IO) {
                _stateFlow.update {
                    it.copy(
                        languageList = getLanguageUseCase(),
                        flagList = getFlagsUseCase(),
                        categoryList = getCategoryListUseCase(),
                    )
                }
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = JokeFormState()
        )


    fun onEvent(event: JokeFormEvent) = viewModelScope.launch {
        when (event) {
            JokeFormEvent.NavigateToJokeResult -> navigator.navigate(
                Destination.Joke(
                    FormData(
                       language = stateFlow.value.languageSelected!!.code,
                       categories = stateFlow.value.categoriesSelected.joinToString(",") {
                           it.alias
                       },
                       flags = stateFlow.value.flagsSelected.joinToString(",") {
                           it.name
                       },
                       amount = stateFlow.value.jokeAmount
                    )
                ),
                navOptions = {
                    popUpTo(Destination.Form) {
                        inclusive = true
                    }
                }
            )

            JokeFormEvent.BackToWelcome -> navigator.navigateUp()
            is JokeFormEvent.ChangeJokeAmount -> {
                _stateFlow.update {
                    it.copy(jokeAmount = event.amount)
                }
            }
            is JokeFormEvent.SelectCategory -> {
                val newList: List<Category> = if (stateFlow.value.categoriesSelected.contains(event.category)) {
                    stateFlow.value.categoriesSelected - event.category
                } else {
                    stateFlow.value.categoriesSelected + event.category
                }
                _stateFlow.update {
                    it.copy(categoriesSelected = newList)
                }
            }

            is JokeFormEvent.SelectFlag -> {
                val newList: List<Flag> = if (stateFlow.value.flagsSelected.contains(event.flag)) {
                    stateFlow.value.flagsSelected - event.flag
                } else {
                    stateFlow.value.flagsSelected + event.flag
                }
                _stateFlow.update {
                    it.copy(flagsSelected = newList)
                }
            }

            is JokeFormEvent.SelectLanguage -> {
                _stateFlow.update {
                    it.copy(
                        languageSelected = event.language,
                        languageSelectorVisibility = false
                    )
                }
            }

            JokeFormEvent.HideLanguageSelector -> _stateFlow.update {
                it.copy(languageSelectorVisibility = false)
            }

            JokeFormEvent.ChangeChipVisibility -> _stateFlow.update {
                it.copy(languageSelectorVisibility = !stateFlow.value.languageSelectorVisibility)
            }
        }
    }
}