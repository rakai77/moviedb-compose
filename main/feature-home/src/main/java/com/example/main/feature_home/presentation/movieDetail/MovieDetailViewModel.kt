package com.example.main.feature_home.presentation.movieDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movieDetailState = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val movieDetailState = _movieDetailState.asStateFlow()

    private val _creditsState = MutableStateFlow<MovieDetailState>(MovieDetailState.Idle)
    val creditsState = _creditsState.asStateFlow()

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            _movieDetailState.update {
                MovieDetailState.LoadingMovieDetail(isLoading = true)
            }
            movieUseCase.getMovieDetail(id).collect { result ->
                _movieDetailState.update {
                    MovieDetailState.LoadingMovieDetail(isLoading = false)
                }
                when(result){
                    is BaseResult.Success -> {
                        _movieDetailState.update {
                            MovieDetailState.SuccessMovieDetail(movieDetail = result.data)
                        }
                    }
                    is BaseResult.Error -> {
                        _movieDetailState.update {
                            MovieDetailState.Error(errorMessage = result.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun getCredits(id: String) {
        viewModelScope.launch {
            _creditsState.update {
                MovieDetailState.LoadingCredits(isLoading = true)
            }
            movieUseCase.getMovieCredits(id).collect { result ->
                _creditsState.update {
                    MovieDetailState.LoadingCredits(isLoading = false)
                }
                when(result){
                    is BaseResult.Success -> {
                        _creditsState.update {
                            MovieDetailState.SuccessCredits(credits = result.data)
                        }
                    }
                    is BaseResult.Error -> {
                        _creditsState.update {
                            MovieDetailState.Error(errorMessage = result.errorMessage)
                        }
                    }
                }
            }
        }
    }
}