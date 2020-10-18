package com.zainco.library.services.codetutor

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import com.zainco.library.databinding.ex1.MainActivity


class CodeTutorServiceActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = MainActivity::class.java.simpleName

    private var buttonStart: Button? = null
    private var buttonStop: Button? = null
    private var buttonBind: Button? = null
    private var buttonUnBind: Button? = null
    private var buttonGetRandomNumber: Button? = null
    private var textViewthreadCount: TextView? = null
    var count = 0

    private var serviceIntent: Intent? = null

    private var mStopLoop = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_tutor_service)
        Log.i(
            getString(R.string.service_demo_tag),
            "MainActivity thread id: " + Thread.currentThread().id
        )

        buttonStart =
            findViewById<View>(R.id.buttonThreadStarter) as Button
        buttonStop = findViewById<View>(R.id.buttonStopthread) as Button


        textViewthreadCount = findViewById<View>(R.id.textViewthreadCount) as TextView

        buttonStart!!.setOnClickListener(this)
        buttonStop!!.setOnClickListener(this)

        serviceIntent = Intent(applicationContext, CodeTutorMyIntentService::class.java)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.buttonThreadStarter -> {
                mStopLoop = true
                startService(serviceIntent)
            }
            R.id.buttonStopthread -> stopService(serviceIntent)
        }
    }
}