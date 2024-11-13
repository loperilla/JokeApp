package io.loperilla.jokeapp.data.network.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.statement.request
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.loperilla.jokeapp.data.network.api.JokeApi
import io.loperilla.jokeapp.data.network.impl.JokeApiImpl
import io.loperilla.jokeapp.data.network.utils.baseUrl
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

/*****
 * Project: JokeApp
 * From: io.loperilla.jokeapp.data.network.di
 * Created By Manuel Lopera on 4/10/24 at 16:53
 * All rights reserved 2024
 */

val networkModule = module {
    @OptIn(ExperimentalSerializationApi::class)
    single<Json> {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = true
        }
    }

    single<HttpClient> {

        HttpClient(OkHttp) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("Logger Ktor =>", message)
                    }
                }
                level = LogLevel.INFO
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = baseUrl
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                    Log.d("HTTP requestTime:", "${response.requestTime}")
                    Log.d("HTTP responseTime:", "${response.responseTime}")
                    Log.d("HTTP url:", "${response.request.url}")
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 10000
            }

            install(ContentNegotiation) {
                json(
                    get()
                )
            }
        }
    }

    single<JokeApi> { JokeApiImpl(get(), get()) }
}
