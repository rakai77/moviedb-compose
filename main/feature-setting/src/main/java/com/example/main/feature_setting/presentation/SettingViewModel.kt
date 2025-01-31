package com.example.main.feature_setting.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.local.AppDataStore
import com.example.core.data.local.AppDataStore.Companion.IS_DARK_MODE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingViewModel(
    private val appDataStore: AppDataStore
) : ViewModel() {

    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode = _isDarkMode.asStateFlow()

    init {
        getThemeMode()
    }

    private fun getThemeMode() {
        viewModelScope.launch {
            appDataStore.isDarkMode.collectLatest {
                _isDarkMode.value = it
            }
        }
    }

    fun setThemMode(isDarkMode: Boolean) {
        viewModelScope.launch {
           appDataStore.dataStore(IS_DARK_MODE, isDarkMode)
        }
    }
}