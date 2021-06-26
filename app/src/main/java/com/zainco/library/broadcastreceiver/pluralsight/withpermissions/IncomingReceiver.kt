/*
package com.zainco.library.broadcastreceiver.pluralsight.withpermissions

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.widget.Toast

class IncomingReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)
        when (state) {
            TelephonyManager.EXTRA_STATE_RINGING ->
                Toast.makeText(
                    context, "ringing state", Toast.LENGTH_SHORT
                ).show()
            TelephonyManager.EXTRA_STATE_OFFHOOK ->
                Toast.makeText(
                    context, "received state", Toast.LENGTH_SHORT
                ).show()
            TelephonyManager.EXTRA_STATE_IDLE ->
                Toast.makeText(
                    context, "idle state", Toast.LENGTH_SHORT*/
/*idle means after call ended*//*

                ).show()
        }
    }
}
*/
