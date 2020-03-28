package com.zainco.library.broadcastreceiver.pluralsight.orderedbrs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class OrderedResultReceiver : BroadcastReceiver() {
    val TAG = OrderedResultReceiver::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        if (isOrderedBroadcast) {
            Log.i(TAG, "hello from OrderedResultReceiver")
            Log.i(
                TAG,
                "code $resultCode, data $resultData, bundle ${getResultExtras(true).getString("title")} "
            )
        }
    }
}
