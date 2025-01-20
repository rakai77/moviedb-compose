package com.example.core.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRedirect
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.parameters
import io.ktor.serialization.gson.gson

class HttpClientFactory {

    fun create() = HttpClient(OkHttp) {
        expectSuccess = false

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 30000
            connectTimeoutMillis = 30000
            requestTimeoutMillis = 30000
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MDRkMDZjNjA1YTI3OTRjZjc0ZWFmODUyMGJhNTFiZCIsIm5iZiI6MTczNzM2ODgyOS43NTgwMDAxLCJzdWIiOiI2NzhlMjRmZDQyZjI3Yzc1NGM2NTQ5MmIiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.53FQqZAmj1XFy6WKvVlsPsVYd2NT6R2n_euvB6IfsDY", "")
                }
                sendWithoutRequest { true }
            }
            parameters {
                append("api_key", "604d06c605a2794cf74eaf8520ba51bd")
            }
        }

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                setLenient()
            }
        }

        install(HttpRedirect) {
            checkHttpMethod = true
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d("Http Status: ", "${response.status.value}")
                Log.d("Http response", response.toString())
            }
        }

        defaultRequest {
            host = "https://api.themoviedb.org/3/"
            headers {
                append(HttpHeaders.ContentType, "application/json")
            }
        }
    }
}