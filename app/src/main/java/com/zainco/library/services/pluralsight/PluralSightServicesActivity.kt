package com.zainco.library.services.pluralsight

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class PluralSightServicesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plural_sight_services)
    }

    fun startStartedService(view: View) {
        val intent = Intent(this@PluralSightServicesActivity, MyStartedService::class.java)
        startService(intent)
    }

    fun stopStartedService(view: View) {
        val intent = Intent(this@PluralSightServicesActivity, MyStartedService::class.java)
        stopService(intent)
    }

    fun startIntentService(view: View) {
        val intent = Intent(this@PluralSightServicesActivity, MyIntentService::class.java)
        intent.putExtra("sleepTime",10)
        startService(intent)
    }
}
