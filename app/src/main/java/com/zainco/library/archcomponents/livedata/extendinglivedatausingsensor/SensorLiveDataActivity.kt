package com.zainco.library.archcomponents.livedata.extendinglivedatausingsensor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_sensor_live_data.*

class SensorLiveDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor_live_data)
        val sensorViewModel = ViewModelProviders.of(this).get(SensorViewModel::class.java)
        // Observe the sensor vale
        sensorViewModel.getSensorData().observe(this, Observer {
            value.text = it.toString()
        })
    }
}
