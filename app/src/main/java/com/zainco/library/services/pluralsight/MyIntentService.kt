package com.zainco.library.services.pluralsight

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.ResultReceiver
import android.util.Log

class MyIntentService : IntentService("MyWorkerThread") {
    /* intent service provides the default implementation of onStartCommand() method , no need to override it again
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId) and onBind() as well
    }*/
    val TAG = MyIntentService::class.java.simpleName
    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate Thread name " + Thread.currentThread().name)
    }
//works in worker thread
    override fun onHandleIntent(intent: Intent?) {
        Log.i(
            TAG,
            "onHandleIntent Thread name " + Thread.currentThread().name
        )// will log onHandleIntent Thread name IntentService[MyWorkerThread]
        //MyWorkerThread : name that given to the constructor

        val sleepTime: Int = intent?.getIntExtra("sleepTime", 1)!!
        val resultReceiver = intent.getParcelableExtra<ResultReceiver>("receiver")
        var ctr = 1
        //all this loop works in bg thread and no ANR happens in UI thread
        while (ctr <= sleepTime) {
            Log.i(
                TAG,
                "Counter is now " + ctr
            )
            try {
                Thread.sleep(1000)
            } catch (e: Exception) {

            }
            ctr++
        }
    val bundle = Bundle()
    bundle.putString("resultIntentService", "Counter Stopped at $ctr seconds")
    resultReceiver?.send(
        18,
        bundle
    )//this will trigger onReceiveResult method in the result receiver class
}

    /*
    total log
    * onCreate Thread name main
    * onHandleIntent Thread name IntentService[MyWorkerThread]
    * onDestroy Thread name main
    after finishing the work it has been finished automatically , u dont need to stop service manually
    * */
    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy Thread name " + Thread.currentThread().name)
    }
}