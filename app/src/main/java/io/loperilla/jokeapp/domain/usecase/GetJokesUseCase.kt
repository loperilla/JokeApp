package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.domain.model.DomainResult
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.model.joke.JokeModel
import io.loperilla.jokeapp.domain.repository.JokeRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 11/10/24 at 18:06
 * All rights reserved 2024
 */
class GetJokesUseCase(
    private val jokeRepository: JokeRepository
) {
    suspend operator fun invoke(formData: FormData): DomainResult<List<JokeModel>> {
        return jokeRepository.getJoke(formData)
    }
}