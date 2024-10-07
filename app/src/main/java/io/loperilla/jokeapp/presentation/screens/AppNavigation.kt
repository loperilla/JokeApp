package io.loperilla.jokeapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.navigator.ObserveAsEvents
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import io.loperilla.jokeapp.presentation.navigator.routes.NavigationAction
import io.loperilla.jokeapp.presentation.navigator.type.FormDataNavType
import io.loperilla.jokeapp.presentation.screens.jokeform.JokeFormScreen
import io.loperilla.jokeapp.presentation.screens.jokeform.JokeFormViewModel
import io.loperilla.jokeapp.presentation.screens.jokeresult.JokeResultScreen
import io.loperilla.jokeapp.presentation.screens.jokeresult.JokeResultViewModel
import io.loperilla.jokeapp.presentation.screens.welcome.WelcomeScreen
import io.loperilla.jokeapp.presentation.screens.welcome.WelcomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens
 * Created By Manuel Lopera on 4/10/24 at 17:09
 * All rights reserved 2024
 */

@Composable
fun AppNavigation(
    navigator: Navigator,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    ObserveAsEvents(flow = navigator.navigationActions) { action ->
        when (action) {
            is NavigationAction.Navigate -> navController.navigate(
                action.route
            ) {
                action.navOptions(this)
            }

            NavigationAction.NavigateUp -> navController.navigateUp()
        }
    }
    NavHost(
        navController = navController,
        startDestination = navigator.startDestination,
        modifier = modifier
    ) {
        composable<Destination.Welcome> {
            val viewModel = koinViewModel<WelcomeViewModel>()
            WelcomeScreen(
                viewModel::onEvent
            )
        }

        composable<Destination.Form> {
            val viewModel = koinViewModel<JokeFormViewModel>()
            val state by viewModel.stateFlow.collectAsStateWithLifecycle()
            JokeFormScreen(
                state,
                viewModel::onEvent
            )
        }

        composable<Destination.Joke>(
            typeMap = mapOf(
                typeOf<FormData>() to FormDataNavType
            )
        ) {
            val formData = it.toRoute<Destination.Joke>().formData
            val viewModel = koinViewModel<JokeResultViewModel>(
                parameters = { parametersOf(formData) }
            )

            val state by viewModel.stateFlow.collectAsStateWithLifecycle()
            JokeResultScreen(
                state,
                viewModel::onEvent
            )
        }
    }
}