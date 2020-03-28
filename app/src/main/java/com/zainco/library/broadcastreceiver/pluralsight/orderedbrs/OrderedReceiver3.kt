package com.zainco.library.broadcastreceiver.pluralsight.orderedbrs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class OrderedReceiver3 : BroadcastReceiver() {
    val TAG = OrderedReceiver3::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        if (isOrderedBroadcast) {
            Log.i(TAG, "hello from OrderedReceiver3")
            Log.i(
                TAG,
                "code $resultCode, data $resultData, bundle ${getResultExtras(true).getString("title")} "
            )
            /* resultCode = 47
             resultData = "black berry"*/
            val bundle = Bundle()
            bundle.putString("title", "lazy developer")
//            setResultExtras(bundle)
            //previous 5 lines could be
            setResult(83, "windows  ", bundle)
        }
    }
}
