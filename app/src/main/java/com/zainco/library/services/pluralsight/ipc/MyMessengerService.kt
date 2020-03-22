package com.zainco.library.services.pluralsight.ipc

import android.app.Service
import android.content.Intent
import android.os.*
import android.widget.Toast

/*
* remote service that actually running on its own process*/
class MyMessengerService : Service() {

    //simply receive the msg coming from activity in another process
    // we can't send msg from this activity to the service  except by using IncomingHandler
    inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val bundle: Bundle;
            when (msg.what) {
                43 -> {
                    bundle = msg.data
                    val numOne = bundle.getInt("numOne", 0)
                    val numTwo = bundle.getInt("numTwo", 0)
                    val sum = add(numOne, numTwo)
                    Toast.makeText(
                        applicationContext,
                        sum,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    val messenger = Messenger(IncomingHandler())

    override fun onBind(intent: Intent): IBinder {
        //should never return null it should return the binder object
        return messenger.binder
        /*executed after we call bindService from the activity and then will be received as iBinder
        * here in the serviceConnection method onServiceConnected(name: ComponentName?, iBinder: IBinder?)*/
    }

    fun add(a: Int, b: Int): Int = a + b

}
