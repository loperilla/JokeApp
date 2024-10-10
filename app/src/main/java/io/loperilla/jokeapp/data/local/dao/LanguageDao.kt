package io.loperilla.jokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.loperilla.jokeapp.data.local.entity.LanguageEntity

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.local.dao
 * Created By Manuel Lopera on 9/10/24 at 20:34
 * All rights reserved 2024
 */
@Dao
interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(language: LanguageEntity)

    @Query("SELECT * FROM LANGUAGEENTITY")
    suspend fun getLanguages(): List<LanguageEntity>
}