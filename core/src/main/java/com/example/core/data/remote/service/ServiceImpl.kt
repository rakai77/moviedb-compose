package com.example.core.data.remote.service

import com.example.core.data.remote.HttpRoute
import com.example.core.data.remote.response.AllTrendingResponse
import com.example.core.data.remote.response.CastResponse
import com.example.core.data.remote.response.CreditsResponse
import com.example.core.data.remote.response.MovieDetailResponse
import com.example.core.data.remote.response.MovieResponse
import com.example.core.data.remote.response.SeriesDetailResponse
import com.example.core.data.remote.response.SeriesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class ServiceImpl(
    private val httpClient: HttpClient,
) : Service {
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

    override suspend fun getPopularCast(page: Int): CastResponse {
        return httpClient.get {
            url(HttpRoute.POPLAR_CAST)
            parameter("page", page)
        }.body()
    }

    override suspend fun getCastFromSearch(query: String, page: Int): CastResponse {
        return httpClient.get {
            url(HttpRoute.SEARCH_CAST)
            parameter("query", query)
            parameter("page", page)
        }.body()
    }

    override suspend fun getMovieDetail(movieId: String): MovieDetailResponse {
        return httpClient.get {
            url(HttpRoute.MOVIE_DETAIL.replace("{movie_id}", movieId))
        }.body()
    }

    override suspend fun getSeriesDetail(seriesId: String): SeriesDetailResponse {
        return httpClient.get {
            url(HttpRoute.SERIES_DETAIL.replace("{series_id}", seriesId))
        }.body()
    }

    override suspend fun getMovieCredits(movieId: String): CreditsResponse {
        return httpClient.get {
            url(HttpRoute.MOVIE_CREDITS.replace("{movie_id}", movieId))
        }.body()
    }

}