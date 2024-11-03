package io.loperilla.jokeapp

import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.model.Language

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp
 * Created By Manuel Lopera on 12/10/24 at 18:33
 * All rights reserved 2024
 */

val languageEN = Language(
    code = "en",
    name = "English"
)

val languageES = Language(
    code = "es",
    name = "Spanish"
)

val nsfwFlag = Flag(
    name = "nsfw"
)

val politicalFlag = Flag(
    name = "political"
)

val programmingCategory = Category(
    alias = "Programming",
    resolvedName = "Programming"
)

val miscellaneousCategory = Category(
    alias = "Miscellaneous",
    resolvedName = "Miscellaneous"
)

