package com.example.core.di

import com.example.core.data.local.AppDataStore
import com.example.core.data.remote.HttpClientFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { HttpClientFactory.create() }
    single { AppDataStore(androidContext()) }
}