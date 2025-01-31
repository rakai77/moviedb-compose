package com.example.core.di

import com.example.core.data.local.AppDataStore
import com.example.core.data.remote.HttpClientFactory
import com.example.core.data.remote.repository.CastRepositoryImpl
import com.example.core.data.remote.repository.HomeRepositoryImpl
import com.example.core.data.remote.service.Service
import com.example.core.data.remote.service.ServiceImpl
import com.example.core.domain.repository.CastRepository
import com.example.core.domain.repository.HomeRepository
import com.example.core.domain.usecase.CastUseCase
import com.example.core.domain.usecase.HomeUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientFactory.create() }
    single { AppDataStore(androidContext()) }
    single<Service> { ServiceImpl(get()) }
    single<HomeRepository> { HomeRepositoryImpl(get()) }
    single { HomeUseCase(get()) }
    single<CastRepository> { CastRepositoryImpl(get()) }
    single { CastUseCase(get()) }
}