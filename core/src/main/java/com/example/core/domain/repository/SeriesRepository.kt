package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    suspend fun getSeriesDetail(movieId: String) : Flow<BaseResult<MovieDetail>>
}