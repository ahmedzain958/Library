package com.zainco.library.services.pluralsight.ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_my_messenger.*

class MyMessengerActivity : AppCompatActivity() {
    var isBound = false
    var messengerService: Messenger? = null

    inner class IncomingResponseHandler : Handler() {
        override fun handleMessage(msgFromService: Message) {
            super.handleMessage(msgFromService)
            when (msgFromService.what) {
                87 -> {
                    val bundle = msgFromService.data
                    val result = bundle.getInt("result", 0)
                    runOnUiThread {
                        textViewResult.text = result.toString()
                    }
                }
            }
        }
    }

    val incomingMessenger = Messenger(IncomingResponseHandler())
    val serviceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
            /*
            * with the help of this binder
            * messenger.binder in onBind method in the service we can get the service messenger here*/
            messengerService = Messenger(iBinder)
            isBound = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_messenger)
    }

    fun performAdd(view: View) {
        //with the help of this messenger reference we can send a msg to the remote process
        val msgToService = Message.obtain(/*hendler*/null, 43)
        val bundle = Bundle()
        bundle.putInt("numOne", editText1.text.toString().toInt())
        bundle.putInt("numTwo", editText2.text.toString().toInt())
        msgToService.data = bundle
        msgToService.replyTo = incomingMessenger
        messengerService?.send(msgToService)//triggers the IncomingHandler inside the service and sends the msgToService to the Handler
    }

    fun bindService(view: View) {
        //calls onBind method inside the service class
        val intent = Intent(this, MyMessengerService::class.java)
        bindService(
            intent,
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    fun unBindService(view: View) {
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }
}
