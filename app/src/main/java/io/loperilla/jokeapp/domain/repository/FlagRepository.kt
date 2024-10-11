package io.loperilla.jokeapp.domain.repository

import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Flag
/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.repository
 * Created By Manuel Lopera on 6/10/24 at 14:02
 * All rights reserved 2024
 */
interface FlagRepository {
    suspend fun getFlag(): DomainResult<List<Flag>>
}