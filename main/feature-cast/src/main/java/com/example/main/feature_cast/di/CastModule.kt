package com.example.main.feature_cast.di

import com.example.main.feature_cast.presentation.CastViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val castModule = module {
    viewModel { CastViewModel(get()) }
}