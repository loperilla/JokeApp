package io.loperilla.jokeapp.presentation.screens.welcome

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
sealed class WelcomeEvent {
    data object NavigateToJokeForm : WelcomeEvent()
}