package com.example.core.domain.repository

import com.example.core.data.BaseResult
import com.example.core.domain.model.Credits
import com.example.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieDetails(movieId: String): Flow<BaseResult<MovieDetail>>

    suspend fun getMovieCredits(movieId: String): Flow<BaseResult<Credits>>
}