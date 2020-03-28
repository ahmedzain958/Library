package com.zainco.library.broadcastreceiver.pluralsight.orderedbrs

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import com.zainco.library.broadcastreceiver.pluralsight.MyFirstReceiver

class OrderedBRsActivity : AppCompatActivity() {
    val TAG = MyFirstReceiver::class.java.simpleName
    val ACTION = "my.action.name"
    lateinit var receiver1: OrderedReceiver1
    lateinit var receiver2: OrderedReceiver2
    lateinit var receiver3: OrderedReceiver3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordered_b_rs)
        receiver1 = OrderedReceiver1()
        receiver2 = OrderedReceiver2()
        receiver3 = OrderedReceiver3()
        val filter = IntentFilter(ACTION)
        registerReceiver(receiver1, filter)
        registerReceiver(receiver2, filter)
        registerReceiver(receiver3, filter)
    }

    fun send(view: View) {
        val intent = Intent(ACTION)
        val bundle = Bundle()
        bundle.putString("title", "smart developer")
        sendOrderedBroadcast(
            intent,
            null,
            OrderedResultReceiver()/*null:if null then the last setResult() wont be sent from the last receiver, so it is preferred to be set to receiver*/,
            null,
            -1,//I think it should always be -1
            "Android",
            bundle
        )

    }
}
