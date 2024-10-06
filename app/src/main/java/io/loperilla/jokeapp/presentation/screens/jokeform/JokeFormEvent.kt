package io.loperilla.jokeapp.presentation.screens.jokeform

import io.loperilla.jokeapp.domain.model.SelectorLanguage

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
    data class SelectLanguage(val language: SelectorLanguage) : JokeFormEvent()
    data class SelectCategory(val category: Category) : JokeFormEvent()
    data class ChangeJokeAmount(val amount: Int) : JokeFormEvent()
    data class SelectFlag(val flag: Flag) : JokeFormEvent()
}