package com.example.moviedb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.local.AppDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val appDataStore: AppDataStore) : ViewModel() {

    init {
        getDarkMode()
    }

    private val _darkModeState = MutableStateFlow(false)
    val darkModeState = _darkModeState.asStateFlow()

    fun storeDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            appDataStore.dataStore(AppDataStore.IS_DARK_MODE, isDarkMode)
        }
    }

    private fun getDarkMode() {
        viewModelScope.launch {
            appDataStore.isDarkMode.collect {
                _darkModeState.value = it
            }
        }
    }
}