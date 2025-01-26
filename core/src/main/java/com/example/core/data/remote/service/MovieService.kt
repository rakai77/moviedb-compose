package com.example.core.data.remote.service

import com.example.core.data.remote.response.AllTrendingResponse
import com.example.core.data.remote.response.MovieResponse

interface MovieService {

    suspend fun getAllTrending() : AllTrendingResponse

    suspend fun getMoviePopular() : MovieResponse

}