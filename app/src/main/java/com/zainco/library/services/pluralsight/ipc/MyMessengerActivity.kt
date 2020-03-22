package com.zainco.library.services.pluralsight.ipc

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_my_messenger.*

class MyMessengerActivity : AppCompatActivity() {
    var isBound = false
    lateinit var messengerService: Messenger
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
        messengerService.send(msgToService)//triggers the IncomingHandler inside the service and sends the msgToService to the Handler
    }

    fun bindService(view: View) {
        //calls onBind method inside the service class
        bindService(
            Intent(this, MyMessengerService::class.java),
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
