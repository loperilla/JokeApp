package io.loperilla.jokeapp.domain.model

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.domain.model
 * Created By Manuel Lopera on 4/10/24 at 16:21
 * All rights reserved 2024
 */
sealed class DomainResult<T> {
    data class Success<T>(val data: T) : DomainResult<T>()
    data class Error<T>(val error: DomainError) : DomainResult<T>()
}

sealed class DomainError(
    val code: Int? = null,
    val message: String? = null
) {
    data class NetworkError(val apiCodeError: Int, val apiErrorMessage: String?):
        DomainError(apiCodeError, apiErrorMessage)

    data class UnknownError(val throwable: Throwable?):
        DomainError(null, throwable?.message ?: "")
}
