package com.example.core.data.remote.service

import com.example.core.data.remote.HttpRoute
import com.example.core.data.remote.response.AllTrendingResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class MovieServiceImpl(
    private val httpClient: HttpClient,
) : MovieService {
    override suspend fun getAllTrending(): AllTrendingResponse {
        return httpClient.get {
            url(HttpRoute.ALL_TRENDING)
        }.body()
    }
}