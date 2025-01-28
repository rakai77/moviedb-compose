package com.example.core.data.remote.service

import com.example.core.data.remote.response.AllTrendingResponse
import com.example.core.data.remote.response.CastResponse
import com.example.core.data.remote.response.MovieResponse
import com.example.core.data.remote.response.SeriesResponse

interface MovieService {

    suspend fun getAllTrending() : AllTrendingResponse
    suspend fun getMoviePopular() : MovieResponse
    suspend fun getOnAirSeries() : SeriesResponse
    suspend fun getCast(query: String, page: Int) : CastResponse

}