package com.zainco.library.broadcastreceiver.pluralsight.dynamicreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_dynamic_receiver.*

class DynamicReceiverActivity : AppCompatActivity() {
    lateinit var dynamicReciever: DynamicReciever
    lateinit var dynamicTimerReciever: DynamicTimerReciever
    var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_receiver)
        dynamicReciever = DynamicReciever()
        dynamicTimerReciever = DynamicTimerReciever()
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(dynamicReciever, intentFilter)
        val intentFilter2 = IntentFilter()
        intentFilter2.addAction(Intent.ACTION_TIME_TICK)//receives receiver every 1 min
        registerReceiver(dynamicTimerReciever, intentFilter2)
    }

    override fun onPause() {
        super.onPause()
/*if forget to unregister
* leaked IntentReceiver com.zainco.library.broadcastreceiver.pluralsight.dynamicreceiver.DynamicReceiverActivity$DynamicReciever@f324c0b that was originally registered here. Are you missing a call to unregisterReceiver()*/
        unregisterReceiver(dynamicReciever)
        unregisterReceiver(dynamicTimerReciever)
    }

    //no need to be put in manifest
    class DynamicReciever : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(
                context,
                "ACTION_AIRPLANE_MODE_CHANGED",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    inner class DynamicTimerReciever : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            textView6.text = "$counter minute over"
            counter++
        }

    }
}
