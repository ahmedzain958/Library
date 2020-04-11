package com.zainco.library.services.neverending

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class NeverEndingActivity : AppCompatActivity() {
    lateinit var mServiceIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_never_ending)
        mServiceIntent = Intent(this, SensorService::class.java)
        if (!isMyServiceRunning(SensorService::class.java)) {
            startService(mServiceIntent)
        }
    }

    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                Log.i("isMyServiceRunning?", true.toString() + "")
                return true
            }
        }
        Log.i("isMyServiceRunning?", false.toString() + "")
        return false
    }


    override fun onDestroy() {
        stopService(mServiceIntent)
        Log.i("MAINACT", "onDestroy!")
        super.onDestroy()
    }

    fun stopService(view: View) {
        stopService(mServiceIntent)
    }
}
