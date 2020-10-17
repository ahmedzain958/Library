package com.zainco.library.services.pluralsight.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_bound_service_plural_sight.*

class BoundServicePluralSightActivity : AppCompatActivity() {
    var isBound = false
    lateinit var myBoundService: MyBoundService

    // to establish a connection between the calling component and the service, we need to make use of a special class of ServiceConnection
    val serviceConnection =
        object : ServiceConnection /*when a service disconnected to the activity*/ {
            override fun onServiceDisconnected(name: ComponentName?) {
                isBound = false
            }

            override fun onServiceConnected(
                name: ComponentName?,
                iBinder: IBinder?
            )/*when a connection established between the activity and the service*/ {
                val myLocalBinder = iBinder as MyBoundService.MyLocalBinder
                myBoundService = myLocalBinder.getService()
                isBound = true
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bound_service_plural_sight)
    }

    override fun onStart() {// whenever this activ. starts we need it to bind to the service
        super.onStart()
        val intent = Intent(this@BoundServicePluralSightActivity, MyBoundService::class.java)
        //whenever this method is executed the onBind() method in service class is also executed
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (isBound){
            unbindService(serviceConnection)
            isBound = false
        }
    }
    fun onCLick(view: View) {
        if (isBound) {
            when (view.id) {
                R.id.buttonAdd -> textViewResult.text = myBoundService.add(
                    editText1.text.toString().toInt(),
                    editText2.text.toString().toInt()
                ).toString()
                R.id.buttonSubtract -> textViewResult.text = myBoundService.sub(
                    editText1.text.toString().toInt(),
                    editText2.text.toString().toInt()
                ).toString()
                R.id.buttonMultiply -> textViewResult.text = myBoundService.mul(
                    editText1.text.toString().toInt(),
                    editText2.text.toString().toInt()
                ).toString()
                R.id.buttonDivide -> textViewResult.text = myBoundService.div(
                    editText1.text.toString().toInt(),
                    editText2.text.toString().toInt()
                ).toString()
            }
        }
    }
}
