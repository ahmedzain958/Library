package com.zainco.library.broadcastreceiver.pluralsight.orderedbrs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class OrderedReceiver1 : BroadcastReceiver() {
    val TAG = OrderedReceiver1::class.java.simpleName
    override fun onReceive(context: Context?, intent: Intent?) {
        if (isOrderedBroadcast) {
            Log.i(TAG, "hello from OrderedReceiver1")
            //getters
            Log.i(
                TAG,
                "code $resultCode, data $resultData, bundle ${getResultExtras(true).getString("title")} "
            )
            //setters to next receiver
            resultCode = 17
            resultData = "ios"
            val bundle = Bundle()
            bundle.putString("title", "Wise developer")
            setResultExtras(bundle)
        }
    }
}