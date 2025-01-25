package com.example.main.feature_home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.BaseResult
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _trendingState = MutableStateFlow<HomeUiState>(HomeUiState.Idle)
    val trendingState = _trendingState.asStateFlow()

    init {
        getAllTrending()
    }

    fun getAllTrending() {
        viewModelScope.launch {
            _trendingState.update {
                HomeUiState.LoadingTrending(isLoading = true)
            }
            movieUseCase.getAllTrending().collect { result ->
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
}