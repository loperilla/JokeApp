package io.loperilla.jokeapp.data.network.utils

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.loperilla.jokeapp.data.network.model.ApiResult
import io.loperilla.jokeapp.data.network.model.ApiResultError
import kotlinx.serialization.json.Json

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.utils
 * Created By Manuel Lopera on 4/10/24 at 16:31
 * All rights reserved 2024
 */

suspend inline fun <reified T> processResponse(
    json: Json,
    call: () -> HttpResponse
): ApiResult<T> {
    return try {
        val httpResponse = call.invoke()
        val body = httpResponse.bodyAsText()
        if (httpResponse.status.value in 200..299) {
            ApiResult.Success(
                data = json.decodeFromString<T>(
                    body
                )
            )
        } else {
            ApiResult.Error(
                ApiResultError.NetworkError(
                    httpResponse.status.value,
                    httpResponse.status.description
                )
            )
        }
    } catch (e: RedirectResponseException) {
        println("Error: ${e.response.status.description}")
        ApiResult.Error(
            ApiResultError.UnknownError(throwable = e)
        )
    } catch (e: ClientRequestException) {
        println("Error: ${e.response.status.description}")
        ApiResult.Error(ApiResultError.UnknownError(throwable = e))
    } catch (e: ServerResponseException) {
        println("Error: ${e.response.status.description}")
        ApiResult.Error(ApiResultError.UnknownError(throwable = e))
    } catch (e: Exception) {
        println("Error: ${e.message}")
        ApiResult.Error(ApiResultError.UnknownError(throwable = e))
    }
}
