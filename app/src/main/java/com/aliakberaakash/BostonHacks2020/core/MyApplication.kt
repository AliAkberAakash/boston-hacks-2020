package com.aliakberaakash.BostonHacks2020.core

import android.app.Application
import com.aliakberaakash.BostonHacks2020.BuildConfig
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}