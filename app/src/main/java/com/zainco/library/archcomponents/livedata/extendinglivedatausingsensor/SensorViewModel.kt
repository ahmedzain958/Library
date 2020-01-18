package com.zainco.library.archcomponents.livedata.extendinglivedatausingsensor

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SensorViewModel(application: Application) : AndroidViewModel(application) {
    private val sensorLiveData = SensorLiveData(application)

    fun getSensorData() = sensorLiveData
}