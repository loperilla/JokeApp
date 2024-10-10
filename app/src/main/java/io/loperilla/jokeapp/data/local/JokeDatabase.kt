package io.loperilla.jokeapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.loperilla.jokeapp.data.local.dao.CategoryDao
import io.loperilla.jokeapp.data.local.dao.FlagDao
import io.loperilla.jokeapp.data.local.dao.LanguageDao
import io.loperilla.jokeapp.data.local.entity.CategoryEntity
import io.loperilla.jokeapp.data.local.entity.FlagEntity
import io.loperilla.jokeapp.data.local.entity.LanguageEntity

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.local
 * Created By Manuel Lopera on 9/10/24 at 20:24
 * All rights reserved 2024
 */
@Database(
    entities = [
        CategoryEntity::class,
        FlagEntity::class,
        LanguageEntity::class
    ],
    version = 1)
abstract class JokeDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun flagDao(): FlagDao
    abstract fun languageDao(): LanguageDao
}