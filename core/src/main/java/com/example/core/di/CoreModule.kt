package com.example.core.di

import com.example.core.data.local.AppDataStore
import com.example.core.data.remote.HttpClientFactory
import com.example.core.data.remote.repository.CastRepositoryImpl
import com.example.core.data.remote.repository.MovieRepositoryImpl
import com.example.core.data.remote.service.MovieService
import com.example.core.data.remote.service.MovieServiceImpl
import com.example.core.domain.repository.CastRepository
import com.example.core.domain.repository.MovieRepository
import com.example.core.domain.usecase.CastUseCase
import com.example.core.domain.usecase.MovieUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientFactory.create() }
    single { AppDataStore(androidContext()) }
    single<MovieService> { MovieServiceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { MovieUseCase(get()) }
    single<CastRepository> { CastRepositoryImpl(get()) }
    single { CastUseCase(get()) }
}