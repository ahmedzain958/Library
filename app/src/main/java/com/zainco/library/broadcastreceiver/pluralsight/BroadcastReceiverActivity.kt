package com.zainco.library.broadcastreceiver.pluralsight

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class BroadcastReceiverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
    }

    fun sendBroadcastMessage(view: View) {
        val intent = Intent(this, MyFirstReceiver::class.java)
        sendBroadcast(intent)
    }
}
