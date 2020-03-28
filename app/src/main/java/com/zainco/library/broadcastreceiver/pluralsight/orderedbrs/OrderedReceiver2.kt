package com.zainco.library.broadcastreceiver.pluralsight.orderedbrs

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

class OrderedReceiver2 : BroadcastReceiver() {
    val TAG = OrderedReceiver2::class.java.simpleName

    override fun onReceive(context: Context, intent: Intent) {
        if (isOrderedBroadcast) {
            Log.i(TAG, "hello from OrderedReceiver2")
            Log.i(
                TAG,
                "code $resultCode, data $resultData, bundle ${getResultExtras(true).getString("title")} "
            )
            /* resultCode = 47
             resultData = "black berry"*/
            val bundle = Bundle()
            bundle.putString("title", "Good developer")
//            setResultExtras(bundle)
            //previous 5 lines could be
            setResult(47, "black berry", bundle)
            //abortBroadcast() if called the order of brs will not continue
        }
    }
}
