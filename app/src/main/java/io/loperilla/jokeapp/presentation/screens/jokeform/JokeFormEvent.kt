package io.loperilla.jokeapp.presentation.screens.jokeform

import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.model.Language

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
sealed class JokeFormEvent {
    data object NavigateToJokeResult : JokeFormEvent()
    data object BackToWelcome : JokeFormEvent()
    data object HideLanguageSelector : JokeFormEvent()
    data object ChangeChipVisibility : JokeFormEvent()
    data class SelectLanguage(val language: Language) : JokeFormEvent()
    data class SelectCategory(val category: Category) : JokeFormEvent()
    data object PlusAmount : JokeFormEvent()
    data object MinusAmount : JokeFormEvent()
    data class SelectFlag(val flag: Flag) : JokeFormEvent()
}