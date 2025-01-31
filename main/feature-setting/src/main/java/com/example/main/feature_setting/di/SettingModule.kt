package com.example.main.feature_setting.di

import com.example.main.feature_setting.presentation.SettingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingModule = module {
    viewModel { SettingViewModel(get()) }
}