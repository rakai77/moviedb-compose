package com.example.main.feature_home.di

import com.example.main.feature_home.presentation.HomeViewModel
import com.example.main.feature_home.presentation.movieDetail.MovieDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { MovieDetailViewModel(get()) }
}