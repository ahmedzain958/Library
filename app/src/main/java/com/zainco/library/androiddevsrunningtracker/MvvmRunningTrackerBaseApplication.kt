package com.zainco.library.androiddevsrunningtracker

import android.app.Application
import com.zainco.library.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Ahmed Zain on 7/25/2020.
 */
//note dagger is compile time injected
@HiltAndroidApp
class MvvmRunningTrackerBaseApplication : Application() {
    //all app module dependencies will live as long the application lives
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}