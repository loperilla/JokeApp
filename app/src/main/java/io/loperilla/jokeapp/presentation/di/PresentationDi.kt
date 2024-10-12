package io.loperilla.jokeapp.presentation.di

import io.loperilla.jokeapp.presentation.navigator.DefaultNavigator
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.screens.jokeform.JokeFormViewModel
import io.loperilla.jokeapp.presentation.screens.jokeresult.JokeResultViewModel
import io.loperilla.jokeapp.presentation.screens.welcome.WelcomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.di
 * Created By Manuel Lopera on 5/10/24 at 11:23
 * All rights reserved 2024
 */
private val navigatorModule = module {
    single<Navigator> { DefaultNavigator() }
}

private val viewModelModule = module {
    viewModel { WelcomeViewModel(get()) }
    viewModel {
        JokeFormViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel { parameter ->
        JokeResultViewModel(
            parameter.get(),
            get(),
            get()
        )
    }
}

val presentationModule = listOf(
    navigatorModule, viewModelModule
)

