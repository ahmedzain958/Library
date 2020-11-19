package com.zainco.library.androiddevsrunningtracker.services

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import com.zainco.library.R
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_PAUSE_SERVICE
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.zainco.library.androiddevsrunningtracker.other.Constants.ACTION_STOP_SERVICE
import com.zainco.library.androiddevsrunningtracker.other.Constants.FASTEST_LOCATION_INTERVAL
import com.zainco.library.androiddevsrunningtracker.other.Constants.LOCATION_UPDATE_INTERVAL
import com.zainco.library.androiddevsrunningtracker.other.Constants.NOTIFICATION_CHANNEL_ID
import com.zainco.library.androiddevsrunningtracker.other.Constants.NOTIFICATION_CHANNEL_NAME
import com.zainco.library.androiddevsrunningtracker.other.Constants.NOTIFICATION_ID
import com.zainco.library.androiddevsrunningtracker.other.Constants.TIMER_UPDATE_INTERVAL
import com.zainco.library.androiddevsrunningtracker.other.TrackingUtility
import com.zainco.library.androiddevsrunningtracker.ui.MvvmRunningTrackerActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Ahmed Zain on 9/7/2020.
 */
typealias PolyLine = MutableList<LatLng>
typealias PolyLines = MutableList<PolyLine>

class TrackingService : LifecycleService()/*the reason of extending from LifecycleService not Service is
we will observe on livedata object inside this service and we tell the live data observe fn in which state our tracking service currently is in its lifecycle state*/ {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    var isFirstRun = true
    private val timeRunInSeconds = MutableLiveData<Long>()

    //things that will be put inside the companion object will be used from outside the service class without object of the current service class
    companion object {
        //think this is total laps times
        val timeRunInMillis = MutableLiveData<Long>()

        //static in java
        val isTracking = MutableLiveData<Boolean>() //observe on this live data change from outside

        // just lines of coordinates on the map
        val pathPoints =
            MutableLiveData<PolyLines>()// whenever user pauses the service and resumes it again, no need for drawing the route when it is paused
        // so
    }

    override fun onCreate() {
        super.onCreate()
        postInitialValues()
        fusedLocationProviderClient = FusedLocationProviderClient(this)
        isTracking.observe(this, Observer {
            updateLocationTracking(it)
        })
    }

    @SuppressLint("MissingPermission")
    private fun updateLocationTracking(isTracking: Boolean) {
        if (isTracking) {
            if (TrackingUtility.hasLocationPermissions(this)) {
                val request = LocationRequest().apply {
                    interval = LOCATION_UPDATE_INTERVAL
                    fastestInterval = FASTEST_LOCATION_INTERVAL
                    priority = PRIORITY_HIGH_ACCURACY
                }
                fusedLocationProviderClient.requestLocationUpdates(
                    request,
                    locationCallback,
                    Looper.getMainLooper()
                )
            }
        } else {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        }
    }

    private fun postInitialValues() {
        //initialy we are not tracking
        isTracking.postValue(false)
        pathPoints.postValue(mutableListOf())//empty list cauze we don't have any coordinatesz
        timeRunInSeconds.postValue(0L)
        timeRunInMillis.postValue(0L)
    }

    private fun addEmptyPolyline() = pathPoints.value?.apply {
        add(mutableListOf())
        pathPoints.postValue(this/*refers to the current polylines object*/)
    } ?: pathPoints.postValue(mutableListOf(mutableListOf()))


    private fun addPathPoint(location: Location?) {
        location?.let {
            val pos = LatLng(location.latitude, location.longitude)
            pathPoints.value?.apply {
                //last element is a list (at the beginning it will be the first element)
                last().add(pos)
                pathPoints.postValue(this)//our fragment will be notified by it later on
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    if (isFirstRun) {
                        startForegroundService()
                        isFirstRun = false
                    } else {
                        Timber.d("Resuming service...")
                        startTimer()
                    }
                }
                ACTION_PAUSE_SERVICE -> {
                    Timber.d("Paused service")
                    pauseService()
                }
                ACTION_STOP_SERVICE -> {
                    Timber.d("Stopped service")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun pauseService() {
        isTracking.postValue(false)
        isTimerEnabled = false
    }

    private var isTimerEnabled = false
    private var lapTime = 0L //time taken by the lap
    private var totalTimeRun = 0L
    private var timeStarted = 0L//timestamp when we started the timer
    private var lastSecondTimeStamp = 0L
    private fun startTimer() {
        //service started before, or this is the first start of our service
        addEmptyPolyline()
        isTracking.postValue(true)
        timeStarted = System.currentTimeMillis()
        isTimerEnabled = true
        //tracking current time without calling it with the observers all the time which is a very bad performance wise, instead we will track the current time with coroutines
        //inwhich we delay coroutines for milliseconds which won't be noticeable from the human but  noticeable alot from the computer
        CoroutineScope(Dispatchers.Main).launch {
            while (isTracking.value!!) {
                lapTime =
                    System.currentTimeMillis() - timeStarted//time difference between now and time started
                timeRunInMillis.postValue(totalTimeRun + lapTime)
                if (timeRunInMillis.value!! >= lastSecondTimeStamp + 1000L) {
                    timeRunInSeconds.postValue(timeRunInSeconds.value!! + 1)
                    lastSecondTimeStamp += 1000L
                }
                delay(TIMER_UPDATE_INTERVAL)
            }
            totalTimeRun += lapTime
        }
    }

    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult?) {
            super.onLocationResult(result)
            if (isTracking.value!!) {
                result?.locations?.let { locations ->
                    for (location in locations) {//whenever we receive a new location which is saved oin result variable
                        addPathPoint(location)
                        Timber.d("NEW LOCATION: ${location.latitude}, ${location.longitude}")
                    }
                }
            }
        }
    }

    private fun startForegroundService() {
        startTimer()
        isTracking.postValue(true)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Running App")
            .setContentText("00:00:00")
            .setContentIntent(getMainActivityPendingIntent())

        startForeground(NOTIFICATION_ID, notificationBuilder.build())
    }

    private fun getMainActivityPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MvvmRunningTrackerActivity::class.java).also {
            it.action = ACTION_SHOW_TRACKING_FRAGMENT
        },
        FLAG_UPDATE_CURRENT
    )

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}