package com.example.core.data.remote.service

import com.example.core.data.remote.response.AllTrendingResponse
import com.example.core.data.remote.response.CastResponse
import com.example.core.data.remote.response.CreditsResponse
import com.example.core.data.remote.response.MovieDetailResponse
import com.example.core.data.remote.response.MovieResponse
import com.example.core.data.remote.response.SeriesDetailResponse
import com.example.core.data.remote.response.SeriesResponse

interface Service {

    suspend fun getAllTrending() : AllTrendingResponse
    suspend fun getMoviePopular() : MovieResponse
    suspend fun getOnAirSeries() : SeriesResponse
    suspend fun getPopularCast(page: Int) : CastResponse
    suspend fun getCastFromSearch(query: String, page: Int) : CastResponse

    suspend fun getMovieDetail(movieId: String) : MovieDetailResponse
    suspend fun getSeriesDetail(seriesId: String) : SeriesDetailResponse
    suspend fun getMovieCredits(movieId: String) : CreditsResponse
}