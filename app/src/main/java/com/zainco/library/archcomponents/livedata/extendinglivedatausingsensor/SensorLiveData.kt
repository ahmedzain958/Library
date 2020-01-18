package com.zainco.library.archcomponents.livedata.extendinglivedatausingsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData

/*
In Android, the proximity sensor is primarily used to detect when the user’s face is close to the screen.
This is how the phone screen seems to know to switch off when you hold it up to your ear during phone calls, preventing any errant button presses.
 */
class SensorLiveData(context: Context) : LiveData<Float>(), SensorEventListener {
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val proximitySensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

    override fun onActive() {
        super.onActive()
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)

    }


    override fun onInactive() {
        super.onInactive()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        Log.d("logggg", "accuracy changed")
    }

    override fun onSensorChanged(event: SensorEvent) {
        value = event.values[0]
    }
}