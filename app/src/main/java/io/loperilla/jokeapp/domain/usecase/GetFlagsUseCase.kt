package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.repository.FlagRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 6/10/24 at 14:10
 * All rights reserved 2024
 */
class GetFlagsUseCase(
    private val flagRepository: FlagRepository
) {

    suspend operator fun invoke(): DomainResult<List<Flag>> = flagRepository.getFlag()
}