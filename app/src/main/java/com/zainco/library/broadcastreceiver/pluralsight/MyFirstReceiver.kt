package com.zainco.library.broadcastreceiver.pluralsight

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyFirstReceiver : BroadcastReceiver() {
    val TAG = MyFirstReceiver::class.java.simpleName
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "hello")
    }
}