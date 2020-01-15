package com.zainco.library.archcomponents.lifecycle.casterio.deepdive

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleAwareLogging : LifecycleObserver {
    private val LOG_TAG: String = "LifecycleAwareLogging"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun listeningToCreate() {
        Log.d(LOG_TAG, "OnCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun listeningToDestry() {
        Log.d(LOG_TAG, "OnDestroy")
    }
}