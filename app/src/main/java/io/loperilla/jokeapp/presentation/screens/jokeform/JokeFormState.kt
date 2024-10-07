package io.loperilla.jokeapp.presentation.screens.jokeform

import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.model.Language

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
data class JokeFormState(
    val languageList: List<Language> = emptyList(),
    val languageSelected: Language? = null,
    val flagList: List<Flag> = emptyList(),
    val flagsSelected: List<Flag> = emptyList(),
    val categoryList: List<Category> = emptyList(),
    var categoriesSelected: List<Category> = emptyList(),
    var containsSearch: String = "",
    var languageSelectorVisibility: Boolean = false,
    var jokeAmount: Int = 1
)