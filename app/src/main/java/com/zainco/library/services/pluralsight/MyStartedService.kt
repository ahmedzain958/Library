package com.zainco.library.services.pluralsight

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyStartedService : Service() {
    val TAG = MyStartedService::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate Thread name " + Thread.currentThread().name)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand Thread name " + Thread.currentThread().name)
        val  intentForReceiver = Intent("action.service.to.activity")
        intentForReceiver.putExtra("startServiceResult","zain")
        sendBroadcast(intentForReceiver)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "onBind Thread name " + Thread.currentThread().name)
        return null
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy Thread name " + Thread.currentThread().name)
        super.onDestroy()
    }
}