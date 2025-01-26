package com.example.core.domain.usecase

import com.example.core.data.BaseResult
import com.example.core.domain.model.AllTrending
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieUseCase(
    private val repository: MovieRepository
) {
    suspend fun getAllTrending() : Flow<BaseResult<AllTrending>> {
        return repository.getAllTrending()
    }

    suspend fun getMoviePopular() : Flow<BaseResult<Movie>> {
        return repository.getMoviePopular()
    }
}