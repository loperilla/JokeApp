package io.loperilla.jokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.loperilla.jokeapp.data.local.entity.CategoryEntity

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.local.dao
 * Created By Manuel Lopera on 9/10/24 at 20:34
 * All rights reserved 2024
 */
@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categoryEntity: CategoryEntity): Long

    @Query("SELECT * FROM CATEGORYENTITY")
    suspend fun getCategories(): List<CategoryEntity>
}