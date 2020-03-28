package com.zainco.library.broadcastreceiver.pluralsight

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.zainco.library.R

class BroadcastReceiverActivity : AppCompatActivity() {
    /*note if we have different receivers with the same action name,
    the sequence inwhich they put inside the manifest their intent will be delivered*/
    lateinit var receiver: MyFirstReceiver
    lateinit var receiver2: MyFirstReceiver
    val ACTION1 = "my.custom.action.name"
    val ACTION2 = "my.custom.action.name2"

    //local broadcast receiver example
    lateinit var localBroadcastManager: LocalBroadcastManager
    val ACTION_LOCAL_BR = "my.result.intent"
    //////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
        val filter = IntentFilter(ACTION1)
        val filter2 = IntentFilter(ACTION2)
        receiver = MyFirstReceiver()
        receiver2 = MyFirstReceiver()
        registerReceiver(receiver, filter)
        registerReceiver(receiver2, filter2)

    }

    fun sendBroadcastMessage(view: View) {
        //this is the first method to call broadcast receiver but without calling registerReceiver in oncreate()
        /* val intent = Intent(this, MyFirstReceiver::class.java)
         sendBroadcast(intent)*/

        /*the second way to call the broadcast receiver is using the action
        * by this way you can define as many actions as you wish and define receivers for them
        * I think in earlier versions there was no need to call registerbroadcast() method before calling sendBroadcast()
        * but now after Android Oreo background limitations we should regiterBroadcast() and unregisterBroadcast() before sendBroadcast() */
        val intent = Intent(ACTION1)
        sendBroadcast(intent)
    }

    fun sendBroadcastMessage2(view: View) {
        /* by this way you can define as many actions as you wish and define receivers for them*/
        val intent = Intent(ACTION2)
        sendBroadcast(intent)
        //if we put toast here it will be executed before the onreceiver() of the sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
        unregisterReceiver(receiver2)
    }


    override fun onResume() {
        super.onResume()
//local broadcast receiver example
        localBroadcastManager = LocalBroadcastManager.getInstance(this)
        val filterLocalBR = IntentFilter(ACTION_LOCAL_BR)
        localBroadcastManager.registerReceiver(resultReceiver, filterLocalBR)
        //////////
    }

    override fun onPause() {
        super.onPause()
        localBroadcastManager.unregisterReceiver(resultReceiver)
    }

    //local broadcast receiver example
    fun sumlocalBroadcast(view: View) {
        val intent = Intent(this, MyFirstReceiver::class.java)
        intent.putExtra("a", 10)
        intent.putExtra("b", 20)
        sendBroadcast(intent)
    }

    val resultReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(
                this@BroadcastReceiverActivity,
                intent?.getIntExtra("sum", 0).toString(),
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }
    //////////

}
