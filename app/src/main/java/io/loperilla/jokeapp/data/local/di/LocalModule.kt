package io.loperilla.jokeapp.data.local.di

import androidx.room.Room
import io.loperilla.jokeapp.data.local.JokeDatabase
import io.loperilla.jokeapp.data.local.dao.CategoryDao
import io.loperilla.jokeapp.data.local.dao.FlagDao
import io.loperilla.jokeapp.data.local.dao.LanguageDao
import org.koin.dsl.module

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.local.di
 * Created By Manuel Lopera on 9/10/24 at 20:22
 * All rights reserved 2024
 */

val localModule = module {
    single<JokeDatabase> {
        Room
            .databaseBuilder(
                get(),
                JokeDatabase::class.java,
                JokeDatabase::class.java.simpleName
            ).build()
    }

    single<CategoryDao> { get<JokeDatabase>().categoryDao() }
    single<LanguageDao> { get<JokeDatabase>().languageDao() }
    single<FlagDao> { get<JokeDatabase>().flagDao() }
}