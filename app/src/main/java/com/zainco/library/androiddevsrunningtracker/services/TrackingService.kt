package com.zainco.library.androiddevsrunningtracker.services

import android.content.Intent
import androidx.lifecycle.LifecycleService
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_PAUSE_SERVICE
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_STOP_SERVICE
import timber.log.Timber

/**
 * Created by Ahmed Zain on 9/7/2020.
 */
class TrackingService : LifecycleService()/*the reason of extending from LifecycleService not Service is
we will observe on livedata object inside this service and we tell the live data observe fn in which state our tracking service currently is in its lifecycle state*/ {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE ->
                    Timber.d("Started or resumed service")
                ACTION_PAUSE_SERVICE ->
                    Timber.d("Paused service")
                ACTION_STOP_SERVICE ->
                    Timber.d("Stopped service")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}