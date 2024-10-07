package io.loperilla.jokeapp.domain.usecase

import io.loperilla.jokeapp.domain.model.Language
import io.loperilla.jokeapp.domain.repository.LanguageRepository

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.usecase
 * Created By Manuel Lopera on 6/10/24 at 13:47
 * All rights reserved 2024
 */
class GetLanguageUseCase(
    private val languageRepository: LanguageRepository
) {
    suspend operator fun invoke(): List<Language> = languageRepository.getLanguages()
}