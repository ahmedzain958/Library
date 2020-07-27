package com.zainco.library.runningtracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Ahmed Zain on 7/25/2020.
 */
//note dagger is compile time injected
@HiltAndroidApp
class BaseApplication : Application() {
}