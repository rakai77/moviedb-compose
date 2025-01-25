package com.example.main.feature_home.presentation

import com.example.core.domain.model.AllTrending

sealed class HomeUiState {
    data class SuccessLoadTrending(val trending: AllTrending) : HomeUiState()
    data class ErrorTrending(val errorMessage: String, val statusCode: Int? = null) : HomeUiState()
    data class LoadingTrending(val isLoading: Boolean) : HomeUiState()
    data object Idle : HomeUiState()
}

