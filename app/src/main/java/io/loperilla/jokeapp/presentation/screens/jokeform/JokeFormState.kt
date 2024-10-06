package io.loperilla.jokeapp.presentation.screens.jokeform

import io.loperilla.jokeapp.domain.model.SelectorLanguage

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.presentation.screens.welcome
 * Created By Manuel Lopera on 4/10/24 at 17:23
 * All rights reserved 2024
 */
data class JokeFormState(
    val selectorLanguageList: List<SelectorLanguage> = emptyList(),
    val languageSelected: SelectorLanguage? = null,
    var categoriesSelected: List<Category> = listOf(Category.ANY),
    var flagsSelected: List<Flag> = emptyList(),
    var containsSearch: String = "",
    var languageSelectorVisibility: Boolean = false,
    var jokeAmount: Int = 1
)

enum class Category {
    PROGRAMMING, MISC, DARK, PUN, SPOOKY, CHRISTMAS, ANY
}

enum class Flag {
    NSFW, RELIGIOUS, POLITICAL, RACIST, SEXIST, EXPLICIT
}