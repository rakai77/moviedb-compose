package com.example.main.feature_home.presentation.movieDetail

import com.example.core.domain.model.Credits
import com.example.core.domain.model.MovieDetail

sealed class MovieDetailState {

    data object Idle : MovieDetailState()
    data class SuccessMovieDetail(val movieDetail: MovieDetail) :  MovieDetailState()
    data class LoadingMovieDetail(val isLoading: Boolean) : MovieDetailState()
    data class LoadingCredits(val isLoading: Boolean) : MovieDetailState()
    data class SuccessCredits(val credits: Credits) : MovieDetailState()
    data class Error(val errorMessage: String) : MovieDetailState()
}