package com.zainco.library

import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class DeviceIdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_id)
        val x = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        Timber.d("zainco    $x")
    }
}