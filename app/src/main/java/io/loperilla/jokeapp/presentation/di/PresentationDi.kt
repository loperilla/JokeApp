package io.loperilla.jokeapp.presentation.di

import io.loperilla.jokeapp.presentation.navigator.DefaultNavigator
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.screens.jokeform.JokeFormViewModel
import io.loperilla.jokeapp.presentation.screens.jokeresult.JokeResultViewModel
import io.loperilla.jokeapp.presentation.screens.welcome.WelcomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.di
 * Created By Manuel Lopera on 5/10/24 at 11:23
 * All rights reserved 2024
 */
val navigatorModule = module {
    singleOf(::DefaultNavigator).bind(Navigator::class)
}

val viewModelModule = module {
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::JokeFormViewModel)
    viewModelOf(::JokeResultViewModel)
}

val presentationModule = listOf(
    navigatorModule, viewModelModule
)

