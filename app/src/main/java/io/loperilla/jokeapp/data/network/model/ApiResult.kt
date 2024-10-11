package io.loperilla.jokeapp.data.network.model

import io.loperilla.jokeapp.domain.model.DomainError


/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.model
 * Created By Manuel Lopera on 11/10/24 at 17:21
 * All rights reserved 2024
 */
sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class Error<T>(val error: ApiResultError) : ApiResult<T>()
}

sealed class ApiResultError(
    val code: Int? = null,
    val message: String? = null
) {
    data class NetworkError(val apiCodeError: Int, val apiErrorMessage: String?):
        ApiResultError(apiCodeError, apiErrorMessage)

    data class UnknownError(val throwable: Throwable?):
        ApiResultError(null, throwable?.message ?: "")
}

fun ApiResultError.toDomainError() = when (this) {
    is ApiResultError.NetworkError -> DomainError.NetworkError(apiCodeError, apiErrorMessage)
    is ApiResultError.UnknownError -> DomainError.UnknownError(throwable)
}