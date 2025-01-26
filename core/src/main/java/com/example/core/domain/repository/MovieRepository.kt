package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.AllTrending
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllTrending() : Flow<BaseResult<AllTrending>>

    suspend fun getMoviePopular() : Flow<BaseResult<Movie>>

    suspend fun getOnAirSeries() : Flow<BaseResult<Series>>
}