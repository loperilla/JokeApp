package io.loperilla.jokeapp

import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.Flag
import io.loperilla.jokeapp.domain.model.FormData
import io.loperilla.jokeapp.domain.model.Language
import io.loperilla.jokeapp.domain.model.joke.JokeFlags
import io.loperilla.jokeapp.domain.model.joke.JokeModel

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

val formData = FormData(
    language = languageEN.code,
    flags = listOf(nsfwFlag, politicalFlag).joinToString {
        it.name
    },
    categories = listOf(programmingCategory, miscellaneousCategory).joinToString {
        it.alias
    },
    amount = 1
)

val jokeDummy = JokeModel(
    id = 50,
    safe = true,
    lang = "en",
    category = programmingCategory.resolvedName,
    type = "twopart",
    setup = "Why do programmers wear glasses?",
    delivery = "Because they need to C#",
    flags = JokeFlags(
        nsfw = false,
        religious = false,
        political = false,
        racist = false,
        sexist = false,
        explicit = false
    )
)
