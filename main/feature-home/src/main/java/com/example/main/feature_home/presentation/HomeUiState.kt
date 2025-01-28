package com.example.main.feature_home.presentation

import com.example.core.domain.model.AllTrending
import com.example.core.domain.model.Movie
import com.example.core.domain.model.Series

sealed class HomeUiState {
    data class SuccessLoadTrending(val trending: AllTrending) : HomeUiState()
    data class ErrorTrending(val errorMessage: String, val statusCode: Int? = null) : HomeUiState()
    data class LoadingTrending(val isLoading: Boolean) : HomeUiState()
    data class SuccessLoadMoviePop(val movie: Movie) : HomeUiState()
    data class LoadingMoviePop(val isLoading: Boolean) : HomeUiState()
    data class SuccessOnAirSeries(val series: Series) : HomeUiState()
    data class LoadingOnAirSeries(val isLoading: Boolean) : HomeUiState()
    data object Idle : HomeUiState()
}

