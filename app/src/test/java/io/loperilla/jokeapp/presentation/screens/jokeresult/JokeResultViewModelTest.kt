package io.loperilla.jokeapp.presentation.screens.jokeresult

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.usecase.GetJokesUseCase
import io.loperilla.jokeapp.formData
import io.loperilla.jokeapp.jokeDummy
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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

    private val navigator = mockk<Navigator>(relaxed = true)
    private val getJokeUseCase = mockk<GetJokesUseCase>(relaxed = true)

    private lateinit var viewModel: JokeResultViewModel

    @BeforeEach
    fun setUp() {
        viewModel = JokeResultViewModel(
            formData = formData,
            navigator = navigator,
            getJokesUseCase = getJokeUseCase
        )
    }

    @Test
    fun `When start viewmodel, request joke with form data`() = runTest {
        coEvery { getJokeUseCase(formData = formData) } returns DomainResult.Success(
            listOf(
                jokeDummy
            )
        )

        // ACTION
        val collectedStates = mutableListOf<JokeResultState>()
        val job = launch {
            viewModel.stateFlow.toList(collectedStates) // Recolectamos los estados emitidos en el flujo
        }

        advanceUntilIdle() // Esperamos a que termine la inicialización del flujo

        // ASSERTIONS
        assertThat(collectedStates.first().isLoading).isFalse() // Inicialmente isLoading debería ser falso
        assertThat(collectedStates.first().jokeModels).isEqualTo(listOf()) // Comprobamos que se ha actualizado la lista de chistes

        job.cancel() // Cancelamos el job para limpiar después del test
    }

    @Test
    fun `When user click back, navigateUp is called`() = runTest {
        // GIVEN
        // ACTION
        viewModel.onEvent(JokeResultEvent.BackToWelcome)
        advanceUntilIdle()
        // ASSERTION
        coVerify {
            navigator.navigateUp()
        }
    }

    companion object {


        @JvmStatic
        @BeforeAll
        fun setup() {
            MockKAnnotations.init(this)


        }
    }
}
