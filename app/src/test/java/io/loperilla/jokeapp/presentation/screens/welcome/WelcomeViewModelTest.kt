package io.loperilla.jokeapp.presentation.screens.welcome

import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 12/10/24 at 14:10
 * All rights reserved 2024
 */
@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class WelcomeViewModelTest {
    private lateinit var viewModel: WelcomeViewModel
    private val navigator: Navigator = mockk(relaxed = true)

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = WelcomeViewModel(navigator)
    }

    @AfterEach
    fun afterEach() {
        clearAllMocks()
    }

    @Test
    fun `on navigate to joke form event, then navigate to joke form`() = runTest {
        viewModel.onEvent(WelcomeEvent.NavigateToJokeForm)
        advanceUntilIdle()
        coVerify {
            navigator.navigate(
                Destination.Form
            )
        }
    }
}