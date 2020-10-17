package com.zainco.library.broadcastreceiver.codinginflow

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


class ExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            Toast.makeText(context, "Boot completed", Toast.LENGTH_SHORT).show()
        }
        //40 or 50 or more apps registered to this action of the broadcast which drains the memory or battery,
        // hence this action has been deprecated starting with api level 24
        // (most of these apps don't do something after being started)
        /*
        * Noughat or higher won't listen for this broadcast anymore if your receiver is registered in manifest*/
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
            Toast.makeText(context, "Connectivity changed", Toast.LENGTH_SHORT).show()
        }
    }
}