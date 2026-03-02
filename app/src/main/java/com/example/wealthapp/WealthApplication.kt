package com.example.wealthapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class WealthApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // In production, you might want to plant a different tree for crash reporting
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
