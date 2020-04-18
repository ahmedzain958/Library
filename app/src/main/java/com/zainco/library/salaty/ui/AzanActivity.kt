package com.zainco.library.salaty.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import com.zainco.library.salaty.AzanBroadcastReceiver


class AzanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_azan)
        /*  bottom_navigation.menu.getItem(0).isChecked = true
          bottom_navigation.menu.findItem(R.id.day).setCheckable(true)
          bottom_navigation.menu.findItem(R.id.day).setChecked(true)*/
        /*buttonAlarm.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            if (Build.VERSION.SDK_INT >= 23) {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.hour,
                    timePicker.minute,
                    0
                )
                Log.d(
                    "AhmedZain", "SystemClock.uptimeMillis():" + SystemClock.uptimeMillis() + "\n" +
                            "System.currentTimeMillis():" + System.currentTimeMillis() + "\n"
                            + "calendar.timeInMillis:" + calendar.timeInMillis + "\n"
                )
            } else {
                calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.currentHour,
                    timePicker.currentMinute,
                    0
                )
            }
            setAlarm(calendar.timeInMillis)
        }
        buttonPause.setOnClickListener {
            val intentAzanService = Intent(this, AzanService::class.java)
            intentAzanService.action =
                AzanService.ACTION_PAUSE
            startService(intentAzanService)
        }
        buttonResume.setOnClickListener {
            val intentAzanService = Intent(this, AzanService::class.java)
            intentAzanService.action =
                AzanService.ACTION_RESUME
            startService(intentAzanService)
        }
        buttonStop.setOnClickListener {
            val intentAzanService = Intent(this, AzanService::class.java)
            stopService(intentAzanService)
        }*/
//        bottom_navigation.setupWithNavController(findNavController(this, R.id.nav_host_fragment))
//todo create navigation of bottom..

    }

    private fun setAlarm(timeInMillis: Long) {
        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        //creating a new intent specifying the broadcast receiver
        val i = Intent(this, AzanBroadcastReceiver::class.java)
        //creating a pending intent using the intent
        val pi = PendingIntent.getBroadcast(this, 0, i, 0)
        //setting the repeating alarm that will be fired every day
        am.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pi)
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show()
    }
}
