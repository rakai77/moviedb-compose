package com.example.moviedb

import android.app.Application
import com.example.core.di.coreModule
import com.example.main.feature_home.di.homeModule
import com.example.moviedb.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieApplication)
            androidLogger()
            modules(appModule, coreModule, homeModule)
        }
    }
}