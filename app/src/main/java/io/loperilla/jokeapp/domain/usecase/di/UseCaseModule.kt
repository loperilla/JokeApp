package io.loperilla.jokeapp.domain.usecase.di

import io.loperilla.jokeapp.domain.usecase.GetCategoryListUseCase
import io.loperilla.jokeapp.domain.usecase.GetFlagsUseCase
import io.loperilla.jokeapp.domain.usecase.GetJokesUseCase
import io.loperilla.jokeapp.domain.usecase.GetLanguageUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase.di
 * Created By Manuel Lopera on 6/10/24 at 11:20
 * All rights reserved 2024
 */

val useCaseModule = module {
    factoryOf(::GetLanguageUseCase)
    factoryOf(::GetFlagsUseCase)
    factoryOf(::GetJokesUseCase)
    factoryOf(::GetCategoryListUseCase)
}