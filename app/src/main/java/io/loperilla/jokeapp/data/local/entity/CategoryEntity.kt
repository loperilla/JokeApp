package io.loperilla.jokeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.local.entity
 * Created By Manuel Lopera on 9/10/24 at 20:28
 * All rights reserved 2024
 */
@Entity
data class CategoryEntity(
    @PrimaryKey val alias: String,
    val resolvedName: String
)