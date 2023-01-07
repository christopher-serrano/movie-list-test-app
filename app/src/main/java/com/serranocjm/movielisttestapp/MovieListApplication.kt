package com.serranocjm.movielisttestapp

import android.app.Application
import com.serranocjm.movielisttestapp.di.CoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        CoreModule.init()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MovieListApplication)
        }
    }
}
