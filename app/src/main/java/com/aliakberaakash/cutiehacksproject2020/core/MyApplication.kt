package com.aliakberaakash.cutiehacksproject2020.core

import android.app.Application
import com.aliakberaakash.cutiehacksproject2020.BuildConfig
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}