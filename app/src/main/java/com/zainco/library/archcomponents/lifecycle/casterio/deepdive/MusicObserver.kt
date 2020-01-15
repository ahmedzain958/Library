package com.zainco.library.archcomponents.lifecycle.casterio.deepdive

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MusicObserver(context: Context) : LifecycleObserver {
    private val musicManager: MusicManager by lazy {
        MusicManager(context)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun timeToStartMusic() {
        Log.d("zzzzzzz", "Observer called")
        musicManager.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pauseMusic() {
        musicManager.pause()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun resumeMusic() {
        musicManager.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun stopMusic() {
        musicManager.stop()
    }
}