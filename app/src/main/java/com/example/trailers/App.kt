package com.example.trailers

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class App : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}

