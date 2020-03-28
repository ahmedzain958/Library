package com.zainco.library.broadcastreceiver.pluralsight

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyFirstReceiver : BroadcastReceiver() {
    val TAG = MyFirstReceiver::class.java.simpleName
    val ACTION_LOCAL_BR = "my.result.intent"
    override fun onReceive(context: Context, intent: Intent?) {
        Log.i(TAG, "hello")
        Toast.makeText(context, intent?.action, Toast.LENGTH_SHORT).show()

        val sum = intent?.getIntExtra("a", 0)?.plus(intent.getIntExtra("b", 0))
        val returningIntent = Intent(ACTION_LOCAL_BR)
        returningIntent.putExtra("sum", sum)
        LocalBroadcastManager.getInstance(context).sendBroadcast(returningIntent)
    }
}