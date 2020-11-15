package com.aliakberaakash.boston_hacks.core

import android.app.Application
import com.aliakberaakash.boston_hacks.BuildConfig
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}