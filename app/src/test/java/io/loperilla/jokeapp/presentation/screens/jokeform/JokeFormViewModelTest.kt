package io.loperilla.jokeapp.presentation.screens.jokeform

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.loperilla.jokeapp.MainCoroutineExtension
import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.usecase.GetCategoryListUseCase
import io.loperilla.jokeapp.domain.usecase.GetFlagsUseCase
import io.loperilla.jokeapp.domain.usecase.GetLanguageUseCase
import io.loperilla.jokeapp.languageEN
import io.loperilla.jokeapp.languageES
import io.loperilla.jokeapp.miscellaneousCategory
import io.loperilla.jokeapp.nsfwFlag
import io.loperilla.jokeapp.politicalFlag
import io.loperilla.jokeapp.presentation.navigator.Navigator
import io.loperilla.jokeapp.presentation.navigator.routes.Destination
import io.loperilla.jokeapp.programmingCategory
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.jokeform
 * Created By Manuel Lopera on 12/10/24 at 18:25
 * All rights reserved 2024
 */
@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class JokeFormViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: JokeFormViewModel
    private val navigator = mockk<Navigator>(relaxed = true)
    private val getLanguageUseCase = mockk<GetLanguageUseCase>(relaxed = true)
    private val getCategoryListUseCase = mockk<GetCategoryListUseCase>(relaxed = true)
    private val getFlagsUseCase = mockk<GetFlagsUseCase>(relaxed = true)

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        coEvery { getLanguageUseCase() } returns DomainResult.Success(
            listOf(
                languageEN,
                languageES
            )
        )
        coEvery { getCategoryListUseCase() } returns DomainResult.Success(
            listOf(
                miscellaneousCategory,
                programmingCategory
            )
        )
        coEvery { getFlagsUseCase() } returns DomainResult.Success(
            listOf(
                nsfwFlag,
                politicalFlag
            )
        )
        viewModel = JokeFormViewModel(
            navigator = navigator,
            getLanguageUseCase = getLanguageUseCase,
            getCategoryListUseCase = getCategoryListUseCase,
            getFlagsUseCase = getFlagsUseCase
        )
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `When start viewmodel, all usecases are called and state is updated`() = runTest {
        val job = launch {
            viewModel.stateFlow.collect { state ->
                advanceUntilIdle()
                assertThat(state.languageList).isEqualTo(listOf(languageEN, languageES))
                assertThat(state.categoryList).isEqualTo(
                    listOf(
                        miscellaneousCategory,
                        programmingCategory
                    )
                )
                assertThat(state.flagList).isEqualTo(listOf(nsfwFlag, politicalFlag))
                coVerify { getLanguageUseCase() }
                coVerify { getCategoryListUseCase() }
                coVerify { getFlagsUseCase() }
            }
        }

        job.cancel()
    }

    @Test
    fun `When user click back button, navigateUp is called`() = runTest {
        // ACTION
        viewModel.onEvent(JokeFormEvent.BackToWelcome)
        advanceUntilIdle()
        // ASSERTION

        coVerify {
            navigator.navigateUp()
        }
    }

    @Test
    fun `When user click language selector hide, language selector visibility is false`() =
        runTest {
            // GIVEN
            viewModel.onEvent(JokeFormEvent.HideLanguageSelector)
            val job = launch {
                // ACTION
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.languageSelectorVisibility).isFalse()
                }
            }

            job.cancel()
        }


    @Test
    fun `When user change visibility language selector, language selector visibility is inverse to previous value`() =
        runTest {
            // GIVEN
            viewModel.onEvent(JokeFormEvent.ChangeChipVisibility)
            val job1 = launch {
                // ACTION
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.languageSelectorVisibility).isTrue()
                }
            }

            job1.cancel()

            viewModel.onEvent(JokeFormEvent.ChangeChipVisibility)
            val job2 = launch {
                // ACTION
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.languageSelectorVisibility).isFalse()
                }
            }
            job2.cancel()
        }

    @Test
    fun `When user click plus amount, joke amount is increased`() = runTest {
        // GIVEN
        viewModel.onEvent(JokeFormEvent.PlusAmount)

        // ACTION
        val job = launch {
            viewModel.stateFlow.collect {
                // ASSERTION
                assertThat(it.jokeAmount).isEqualTo(2)
            }
        }

        job.cancel()
    }

    @Test
    fun `When user click minus amount, joke amount is decreased`() = runTest {
        // GIVEN
        viewModel.onEvent(JokeFormEvent.PlusAmount)

        // ACTION
        val job1 = launch {
            viewModel.stateFlow.collect {
                // ASSERTION
                assertThat(it.jokeAmount).isEqualTo(2)
            }
        }

        job1.cancel()

        // GIVEN
        viewModel.onEvent(JokeFormEvent.MinusAmount)

        // ACTION
        val job2 = launch {
            viewModel.stateFlow.collect {
                // ASSERTION
                assertThat(it.jokeAmount).isEqualTo(1)
            }
        }

        job2.cancel()
    }

    @Test
    fun `When select language, selected language is added to state`() = runTest {
        // GIVEN
        viewModel.onEvent(JokeFormEvent.SelectLanguage(languageEN))

        // ACTION
        val job = launch {
            viewModel.stateFlow.collect {
                // ASSERTION
                assertThat(it.languageSelected).isEqualTo(languageEN)
            }
        }

        job.cancel()
    }

    @Test
    fun `When flag previowly selected is deselected, selected flag is removed from flag selected list`() =
        runTest {
            // GIVEN
            viewModel.onEvent(JokeFormEvent.SelectFlag(nsfwFlag))

            // ACTION
            val job1 = launch {
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.flagsSelected).isEqualTo(listOf(nsfwFlag))
                }
            }

            job1.cancel()

            // GIVEN
            viewModel.onEvent(JokeFormEvent.SelectFlag(nsfwFlag))

            // ACTION
            val job2 = launch {
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.flagsSelected).isEqualTo(emptyList())
                }
            }

            job2.cancel()
        }

    @Test
    fun `When category previowsly selected is deselected, selected flag is removed category flag selected list`() =
        runTest {
            // GIVEN
            viewModel.onEvent(JokeFormEvent.SelectCategory(programmingCategory))

            // ACTION
            val job1 = launch {
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.categoriesSelected).isEqualTo(listOf(programmingCategory))
                }
            }

            job1.cancel()

            // GIVEN
            viewModel.onEvent(JokeFormEvent.SelectCategory(programmingCategory))

            // ACTION
            val job2 = launch {
                viewModel.stateFlow.collect {
                    // ASSERTION
                    assertThat(it.categoriesSelected).isEqualTo(emptyList())
                }
            }

            job2.cancel()
        }

    @Test
    fun `When user click submit, submit is called and navigate`() = runTest {
        val job = launch {
            viewModel.onEvent(JokeFormEvent.SelectFlag(nsfwFlag))
            viewModel.onEvent(JokeFormEvent.SelectCategory(programmingCategory))
            viewModel.onEvent(JokeFormEvent.SelectLanguage(languageEN))
            viewModel.onEvent(JokeFormEvent.PlusAmount)
            viewModel.onEvent(JokeFormEvent.PlusAmount)
            viewModel.onEvent(JokeFormEvent.PlusAmount)

            viewModel.onEvent(JokeFormEvent.NavigateToJokeResult)
            advanceUntilIdle()
            val formData = FormData(
                flags = nsfwFlag.name,
                categories = programmingCategory.alias,
                language = languageEN.code,
                amount = 3
            )
            coVerify {
                navigator.navigate(
                    Destination.Joke(formData)
                )
            }
        }

        job.cancel()
    }
}
