package com.zainco.library.broadcastreceiver.pluralsight

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyFirstReceiver : BroadcastReceiver() {
    val TAG = MyFirstReceiver::class.java.simpleName
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i(TAG, "hello")
        Toast.makeText(context, intent?.action, Toast.LENGTH_SHORT).show()
    }
}