package com.zainco.library.threading.codinginflow

import android.os.Bundle
import android.os.Message
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import com.zainco.library.threading.codinginflow.ExampleHandler.Companion.TASK_A
import com.zainco.library.threading.codinginflow.ExampleHandler.Companion.TASK_B

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

    fun stopThread(view: View) {
        looperThread.handler.looper.quit()
        //or         looperThread.handler?.looper.quit()
    }

    fun taskA(view: View) {
        /*this is like when u use handler to post from the background thread to the ui thread but here is the opposite direction here
        we send something from the ui thread to the message queue of the new looper thread
        firstly we start the thread and our ui is responsive and when we click on task A several times it executes the tasks sequentially(ine after another not in the same time)
         because you post a new runnable to it
      */
        //method (1) working with runnable
        /*looperThread.handler.post {
            for (i in 0..5) {
                Log.d(TAG, "run : $i")
                SystemClock.sleep(500)
            }
        }*/
    //method (2) working with messages
        val msg: Message = Message.obtain()
        msg.what = TASK_A
        looperThread.handler.sendMessage(msg)
    }

    fun taskB(view: View) {
        val msg: Message = Message.obtain()
        msg.what = TASK_B
        looperThread.handler.sendMessage(msg)
    }

}
