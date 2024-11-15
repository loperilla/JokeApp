package io.loperilla.jokeapp

import io.loperilla.jokeapp.data.local.entity.CategoryEntity
import io.loperilla.jokeapp.data.local.entity.FlagEntity
import io.loperilla.jokeapp.data.local.entity.LanguageEntity
import io.loperilla.jokeapp.data.network.model.ApiResultError
import io.loperilla.jokeapp.data.network.model.CategoryAliasesApi
import io.loperilla.jokeapp.data.network.model.CategoryApi
import io.loperilla.jokeapp.data.network.model.FlagApi
import io.loperilla.jokeapp.data.network.model.JokeLanguageApi
import io.loperilla.jokeapp.data.network.model.NetworkLanguage
import io.loperilla.jokeapp.data.network.model.joke.JokeFlagsApi
import io.loperilla.jokeapp.data.network.model.joke.JokeListApi
import io.loperilla.jokeapp.data.network.model.joke.JokeModelApi
import io.loperilla.jokeapp.data.network.model.joke.toDomain
import io.loperilla.jokeapp.data.network.model.toDomainError
import io.loperilla.jokeapp.domain.model.Category
import io.loperilla.jokeapp.domain.model.DomainError
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

val formDataWithAmountTwo = formData.copy(amount = 2)

val jokeModelApi = JokeModelApi(
    id = 50,
    safe = true,
    lang = "en",
    category = programmingCategory.resolvedName,
    type = "twopart",
    setup = "Why do programmers wear glasses?",
    delivery = "Because they need to C#",
    flags = JokeFlagsApi(
        nsfw = false,
        religious = false,
        political = false,
        racist = false,
        sexist = false,
        explicit = false
    )
)

val jokeListApi = JokeListApi(
    amount = 2,
    error = false,
    jokes = listOf(jokeModelApi)
)

val jokeDummy = jokeModelApi.toDomain()

val categoryEntity = CategoryEntity(
    alias = "dummy",
    resolvedName = "dummy"
)

val miscellaneousCategoryAliasApi = CategoryAliasesApi(
    alias = "Miscellaneous",
    resolved = "Miscellaneous"
)

val programmingCategoryAliasApi = CategoryAliasesApi(
    alias = "Programming",
    resolved = "Programming"
)

val flagEntity = FlagEntity(
    name = "dummy"
)

val flagApi = FlagApi(
    listOf("dummy")
)

val languageEntity = LanguageEntity(
    code = "dummy",
    name = "dummy"
)

val languageNetworkApi = NetworkLanguage(
    code = "dummy",
    name = "dummy"
)
val languageApi = JokeLanguageApi(
    jokeLanguages = listOf("dummy"),
    possibleLanguages = listOf(languageNetworkApi)
)

val networkApiError = ApiResultError.NetworkError(404, "Not Found")
val networkDomainError = networkApiError.toDomainError()
val unknownDomainError = DomainError.UnknownError(Throwable())