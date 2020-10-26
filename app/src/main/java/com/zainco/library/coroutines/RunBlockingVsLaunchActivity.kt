package com.zainco.library.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.coroutines.*
import timber.log.Timber

class RunBlockingVsLaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_blocking_vs_launch)
        // All the next network calls will take place in the same time
        /*
 2020-10-26 13:03:48.546 28035-28211/com.zainco.library I/RunBlockingVsLaunchActivity: ZAIN-runBlocking call1 started
2020-10-26 13:03:48.566 28035-28214/com.zainco.library I/RunBlockingVsLaunchActivity: ZAIN-GlobalScope call1 started
2020-10-26 13:03:48.571 28035-28213/com.zainco.library I/RunBlockingVsLaunchActivity: ZAIN-runBlocking call2 started
2020-10-26 13:03:48.663 28035-28216/com.zainco.library I/RunBlockingVsLaunchActivity: ZAIN-GlobalScope call2 started
2020-10-26 13:03:50.629 28035-28035/com.zainco.library I/RunBlockingVsLaunchActivity$onCreate: ZAIN-runBlocking call1 + 2
     ZAIN-runBlocking call1 finished
     ZAIN-runBlocking call2 finished
2020-10-26 13:03:50.664 28035-28216/com.zainco.library I/RunBlockingVsLaunchActivity$onCreate: ZAIN-GlobalScope call1 + 2
     ZAIN-GlobalScope call1 finished
     ZAIN-GlobalScope call2 finished
     */
        GlobalScope.launch {
            val networkCall1Result = async(Dispatchers.IO) {
                doNetworkCall1("GlobalScope")
            }
            val networkCall2Result = async(Dispatchers.IO) {
                doNetworkCall2("GlobalScope")
            }
            Timber.i("ZAIN-GlobalScope call1 + 2 \n ${networkCall1Result.await()} \n ${networkCall2Result.await()}")
        }
        runBlocking {
            val networkCall1Result = async(Dispatchers.IO) {
                doNetworkCall1("runBlocking")
            }
            val networkCall2Result = async(Dispatchers.IO) {
                doNetworkCall2("runBlocking")
            }
            Timber.i("ZAIN-runBlocking call1 + 2 \n ${networkCall1Result.await()} \n ${networkCall2Result.await()}")
        }
        ////////////////////////////////ex2
        GlobalScope.launch {
            var result1: String = ""
            var result2: String = ""
            val job1 = launch {
                result1 = doNetworkCall1("GlobalScope")
            }
            val job2 = launch {
                result2 = doNetworkCall2("GlobalScope")
            }
            job1.join()
            job2.join()
            Timber.i("ZAINCO-GlobalScope call1 + 2 \n $result1 \n ${result2}")
        }
        runBlocking {
            var result1: String = ""
            var result2: String = ""
            val job1 = launch {
                result1 = doNetworkCall1("runBlocking")
            }
            val job2 = launch {
                result2 = doNetworkCall2("runBlocking")
            }
            job1.join()
            job2.join()
            Timber.i("ZAINCO-runBlocking call1 + 2 \n $result1 \n $result2")
        }
    }

    /*  suspend fun doNetworkCall1(from: String): String {
          Timber.i("ZAIN-$from call1 started")
          delay(2000)
          return "ZAIN-$from call1 finished"
      }

      suspend fun doNetworkCall2(from: String): String {
          Timber.i("ZAIN-$from call2 started")
          delay(2000)
          return "ZAIN-$from call2 finished"
      }*/
    suspend fun doNetworkCall1(from: String): String {
        Timber.i("ZAINCO-$from call1 started")
        delay(2000)
        return "ZAINCO-$from call1 finished"
    }

    suspend fun doNetworkCall2(from: String): String {
        Timber.i("ZAINCO-$from call2 started")
        delay(2000)
        return "ZAINCO-$from call2 finished"
    }
}