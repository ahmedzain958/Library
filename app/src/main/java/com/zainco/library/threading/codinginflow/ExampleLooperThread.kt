package com.zainco.library.threading.codinginflow

import android.os.Handler
import android.os.Looper
import android.util.Log

class ExampleLooperThread : Thread() {
    private val TAG = "ExampleLooperThread"
    lateinit var handler: Handler
    override fun run() {
        Looper.prepare()//creates a looper for this background thread and automatically creates a msg queue
        handler = Handler()
        //this line  handler = Handler() without a looper the app will crash because handler can't work for a thread that has no looper in its msg queue
        Looper.loop()//this starts the infinite for loop
        /*
        * Looper.prepare()
        handler = Handler()
        Looper.loop()this order is important otherwise the handler can't find a looper for this thread */
        /* for (i in 0..5) {
             Log.d(TAG, "run : $i")
             SystemClock.sleep(1000)
         }*/
        Log.d(TAG, "End of run")
    }
}