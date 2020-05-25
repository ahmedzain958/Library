package com.zainco.library.coroutines

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_coroutines.*
import kotlinx.coroutines.*

/*
* difference between thread.sleep() and coroutines delay() method :
* if we have 2 construction projects(2 threads) and every project has workers(worker = coroutine)
* delay: a worker paused his work from one of the cons. projects
* thread.sleep():project stopped(thread stopped) working so all workers(coroutines) should stop working
* */
//delay functions (suspend functions) called only from inside coroutine blocks or from inside another suspend function
class CoroutinesActivity : AppCompatActivity() {
    val TAG = "CoroutinesActivity"

    //coroutines will be cancelled when main thread finishes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)
        //GlobalScope means that this coroutine will live as long as app does
        GlobalScope.launch {
            //put instr. ur coroutines will execute
            delay(1000L)// workes different from thread.sleep() because it pauses the current coroutine and won't block the whole thread it is working in
            d(TAG, "coroutines says hello from ${Thread.currentThread().name}")
        }
        d(TAG, "hello from ${Thread.currentThread().name}")

        GlobalScope.launch {//both answer will display after  6 seconds
            val doNetworkCall = doNetworkCall()//this will influence the second delay
            val doNetworkCall2 = doNetworkCall2()//will be affected by the first delay
            d(TAG, doNetworkCall)
            d(TAG, doNetworkCall2)
        }
/*
        //Coroutines run in specific contexts; contexts will descibe in which thread our coroutine will be started in
        GlobalScope.launch(Dispatchers.Main) {
            //do ui operations from within ur coroutine, caz u can only change ur ui from ur main thread   }
        }
        GlobalScope.launch(Dispatchers.Default) {
            //complex and long-runing calculations like sort 10000 items
        }
        GlobalScope.launch(Dispatchers.Unconfined) {
            //unconfined to a difinite thread
        }
        GlobalScope.launch(newSingleThreadContext("MyThread")) {
            //runs coroutines in that new thread
        }*/
        GlobalScope.launch(Dispatchers.IO) {
            val answer = doNetworkCall()//this is ex makes a network call for 3 secs
            withContext(Dispatchers.Main) {
                //executed in the main thread
                textView8.text = answer
            }
        }


        //blocks the thread
        runBlocking {

        }
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "method take some time to get the answer - this is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "method take some time to get the answer - this is the answer"
    }
}