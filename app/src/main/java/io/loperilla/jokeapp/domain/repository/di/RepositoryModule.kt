package io.loperilla.jokeapp.domain.repository.di

import io.loperilla.jokeapp.data.repository.CategoryRepositoryImpl
import io.loperilla.jokeapp.data.repository.FlagRepositoryImpl
import io.loperilla.jokeapp.data.repository.LanguageRepositoryImpl
import io.loperilla.jokeapp.domain.repository.CategoryRepository
import io.loperilla.jokeapp.domain.repository.FlagRepository
import io.loperilla.jokeapp.domain.repository.LanguageRepository
import org.koin.dsl.module

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.repository.di
 * Created By Manuel Lopera on 6/10/24 at 13:44
 * All rights reserved 2024
 */

val repositoryModule = module {
    factory<LanguageRepository> { LanguageRepositoryImpl(get()) }
    factory<FlagRepository> { FlagRepositoryImpl(get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get()) }
}