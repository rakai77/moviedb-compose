package com.example.main.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _trendingState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val trendingState = _trendingState.asStateFlow()

    private val _moviePopState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val moviePopState = _moviePopState.asStateFlow()

    private val _onAirSeriesState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val onAirSeriesState = _onAirSeriesState.asStateFlow()

    init {
        getAllTrending()
        getMoviePopular()
        getOnAirSeries()
    }

    fun getAllTrending() {
        viewModelScope.launch {
            _trendingState.update {
                HomeUiState.LoadingTrending(isLoading = true)
            }
            homeUseCase.getAllTrending().collect { result ->
                _trendingState.update {
                    HomeUiState.LoadingTrending(isLoading = false)
                }
                when (result) {
                    is BaseResult.Success -> {
                        _trendingState.update {
                            HomeUiState.SuccessLoadTrending(trending = result.data)
                        }
                    }

                    is BaseResult.Error -> {
                        _trendingState.update {
                            HomeUiState.ErrorTrending(
                                errorMessage = result.errorMessage,
                                statusCode = result.statusCode
                            )
                        }
                    }
                }
            }
        }
    }

    fun getMoviePopular() {
        viewModelScope.launch {
            _moviePopState.update {
                HomeUiState.LoadingMoviePop(isLoading = true)
            }
            homeUseCase.getMoviePopular().collect { result ->
                _moviePopState.update {
                    HomeUiState.LoadingMoviePop(isLoading = false)
                }
                when (result) {
                    is BaseResult.Success -> {
                        _moviePopState.update {
                            HomeUiState.SuccessLoadMoviePop(movie = result.data)
                        }
                    }

                    is BaseResult.Error -> {
                        _moviePopState.update {
                            HomeUiState.ErrorTrending(
                                errorMessage = result.errorMessage,
                                statusCode = result.statusCode
                            )
                        }
                    }
                }
            }
        }
    }

    fun getOnAirSeries() {
        viewModelScope.launch {
            _onAirSeriesState.update {
                HomeUiState.LoadingOnAirSeries(isLoading = true)
            }
            homeUseCase.getOnAirSeries().collect { result ->
                _onAirSeriesState.update {
                    HomeUiState.LoadingOnAirSeries(isLoading = false)
                }
                when (result) {
                    is BaseResult.Success -> {
                        _onAirSeriesState.update {
                            HomeUiState.SuccessOnAirSeries(series = result.data)
                        }
                    }

                    is BaseResult.Error -> {
                        _onAirSeriesState.update {
                            HomeUiState.ErrorTrending(
                                errorMessage = result.errorMessage,
                                statusCode = result.statusCode
                            )
                        }
                    }
                }
            }
        }
    }
}