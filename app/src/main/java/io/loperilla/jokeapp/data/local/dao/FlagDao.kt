package io.loperilla.jokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.loperilla.jokeapp.data.local.entity.FlagEntity

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.local.dao
 * Created By Manuel Lopera on 9/10/24 at 20:34
 * All rights reserved 2024
 */
@Dao
interface FlagDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(flag: FlagEntity)

    @Query("SELECT * FROM FLAGENTITY")
    fun getFlagsList(): List<FlagEntity>
}
