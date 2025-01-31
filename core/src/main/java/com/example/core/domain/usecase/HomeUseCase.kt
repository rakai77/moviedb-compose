package com.example.core.domain.usecase

import com.example.core.data.BaseResult
import com.example.core.domain.model.AllTrending
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Series
import com.example.core.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class HomeUseCase(
    private val repository: HomeRepository
) {
    suspend fun getAllTrending() : Flow<BaseResult<AllTrending>> {
        return repository.getAllTrending()
    }

    suspend fun getMoviePopular() : Flow<BaseResult<Movie>> {
        return repository.getMoviePopular()
    }

    suspend fun getOnAirSeries() : Flow<BaseResult<Series>> {
        return repository.getOnAirSeries()

    }
}