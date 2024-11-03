package io.loperilla.jokeapp.presentation.screens.jokeresult

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.usecase.GetJokesUseCase
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.mockk.MockKAnnotations
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtendWith

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.jokeresult
 * Created By Manuel Lopera on 3/11/24 at 12:14
 * All rights reserved 2024
 */
@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class JokeResultViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        val navigator = mockk<Navigator>(relaxed = true)
        val getJokeUseCase = mockk<GetJokesUseCase>(relaxed = true)

        @JvmStatic
        @BeforeAll
        fun setup() {
            MockKAnnotations.init(this)


        }
    }
}
