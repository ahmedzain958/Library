package com.zainco.library.threading.codinginflow

import android.os.Handler
import android.os.Message
import android.util.Log

class ExampleHandler : Handler() {
    companion object {
        private const val TAG = "ExampleHandler"
        val TASK_A = 1
        val TASK_B = 2
    }

    override fun handleMessage(msg: Message) {
        when (msg.what) {
            TASK_A -> Log.d(TAG, "Task A executed")
            TASK_B -> Log.d(TAG, "Task B executed")
        }
    }
}