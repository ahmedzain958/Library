package com.zainco.library.broadcastreceiver.pluralsight.sticky

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.BatteryManager.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class StickyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky)
    }

    //click eveytime to get status
    fun method1(view: View) {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        val stickyIntent = registerReceiver(
            null,
            intentFilter
        )//sticky intent but we should click on the button every time we need to know the battery status
        val batteryStatus = stickyIntent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        when (batteryStatus) {
            BATTERY_STATUS_UNKNOWN -> Toast.makeText(
                this,
                "BATTERY_STATUS_UNKNOWN",
                Toast.LENGTH_LONG
            ).show()
            BATTERY_STATUS_CHARGING
            -> Toast.makeText(
                this,
                "BATTERY_STATUS_CHARGING",
                Toast.LENGTH_LONG
            ).show()
            BATTERY_STATUS_DISCHARGING
            -> Toast.makeText(
                this,
                "BATTERY_STATUS_DISCHARGING",
                Toast.LENGTH_LONG
            ).show()
            BATTERY_STATUS_NOT_CHARGING
            -> Toast.makeText(
                this,
                "BATTERY_STATUS_NOT_CHARGING",
                Toast.LENGTH_LONG
            ).show()
            BATTERY_STATUS_FULL
            -> Toast.makeText(
                this,
                "BATTERY_STATUS_FULL",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun method2(view: View) {
        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_CHANGED)
        }
        registerReceiver(
            batteryStatusReceiver,
            intentFilter
        )// we will click on the button once not every time to know the battery status
    }

    val batteryStatusReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val batteryStatus = intent?.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
            when (batteryStatus) {
                BATTERY_STATUS_UNKNOWN -> Toast.makeText(
                    context,
                    "BATTERY_STATUS_UNKNOWN",
                    Toast.LENGTH_LONG
                ).show()
                BATTERY_STATUS_CHARGING
                -> Toast.makeText(
                    context,
                    "BATTERY_STATUS_CHARGING",
                    Toast.LENGTH_LONG
                ).show()
                BATTERY_STATUS_DISCHARGING
                -> Toast.makeText(
                    context,
                    "BATTERY_STATUS_DISCHARGING",
                    Toast.LENGTH_LONG
                ).show()
                BATTERY_STATUS_NOT_CHARGING
                -> Toast.makeText(
                    context,
                    "BATTERY_STATUS_NOT_CHARGING",
                    Toast.LENGTH_LONG
                ).show()
                BATTERY_STATUS_FULL
                -> Toast.makeText(
                    context,
                    "BATTERY_STATUS_FULL",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(batteryStatusReceiver)
    }
}
