package com.zainco.library.salaty

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AzanBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val azanServiceIntent = Intent(context, AzanService::class.java)
        azanServiceIntent.action = AzanService.ACTION_PLAY
        context.startService(azanServiceIntent)
    }
}
