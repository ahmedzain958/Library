package com.zainco.library.threading.codinginflow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class ExampleLooperThreadActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val looperThread = ExampleLooperThread()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_looper_thread)
    }

    fun startThread(view: View) {
        looperThread.start()
    }

    fun stopThread(view: View) {}
    fun taskA(view: View) {}
    fun taskB(view: View) {}

}
