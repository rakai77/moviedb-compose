package com.example.core.data.remote.service

import com.example.core.data.remote.HttpRoute
import com.example.core.data.remote.response.AllTrendingResponse
import com.example.core.data.remote.response.MovieResponse
import com.example.core.data.remote.response.SeriesResponse
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

    override suspend fun getMoviePopular(): MovieResponse {
        return httpClient.get {
            url(HttpRoute.MOVIE_POPULAR)
        }.body()
    }

    override suspend fun getOnAirSeries(): SeriesResponse {
        return httpClient.get {
            url(HttpRoute.ON_AIR_SERIES)
        }.body()
    }
}