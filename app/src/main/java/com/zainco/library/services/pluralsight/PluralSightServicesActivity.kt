package com.zainco.library.services.pluralsight

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_plural_sight_services.*

class PluralSightServicesActivity : AppCompatActivity() {
    val handler = Handler()//handler of the main thread
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
        val myResultReceiver = MyResultReceiver(null)
        intent.putExtra("sleepTime", 10)
        intent.putExtra("receiver", myResultReceiver)
        startService(intent)
    }

    override fun onResume() {
        super.onResume()
        val myStartedServiceReceiver = MyStartedServiceReceiver()
        val intentFilter = IntentFilter()
        intentFilter.addAction("action.service.to.activity")
        registerReceiver(myStartedServiceReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        val myStartedServiceReceiver = MyStartedServiceReceiver()
        unregisterReceiver(myStartedServiceReceiver)
    }

    inner class MyStartedServiceReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getStringExtra("startServiceResult")
            button7.text = result
        }

    }

    inner class MyResultReceiver(handler: Handler?) : ResultReceiver(handler) {
        //receives data from intent service class using resultreceiver
        override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
            super.onReceiveResult(resultCode, resultData)
            if (resultCode == 18 && resultData != null) {
                val result = resultData.getString("resultIntentService")
                //onReceiveResult also works in worker thread so we can access ui elements
                button9.post {
                    button9.text = result// button9 text will be "Counter stopped at 10 seconds
                }
            }
        }
    }
}
