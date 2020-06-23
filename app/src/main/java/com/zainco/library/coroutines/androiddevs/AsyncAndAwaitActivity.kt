package com.zainco.library.coroutines.androiddevs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

class AsyncAndAwaitActivity : AppCompatActivity() {
    val TAG = "AsyncAndAwaitActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_and_await)
        //video 7
        /* //===>  GlobalScope.launch {
               val time = measureTimeMillis {
                   val answer1 = doNetworkCall1()
                   val answer2 = doNetworkCall2()
                   //answer 1 and 2 won't be null cuz will wait till newt. call finish then assign values and cuz both called in same coroutines unlike the next ex.
                   Log.d(TAG, "ans 1 $answer1")
                   Log.d(TAG, "ans 2 $answer2")
               }
               Log.d(TAG, "took $time")
           }
           15:14:03.273 9699-9766/com.zainco.library D/AsyncAndAwaitActivity: ans 1 method take some time to get the answer - this is the answer
           15:14:03.273 9699-9766/com.zainco.library D/AsyncAndAwaitActivity: ans 2 method take some time to get the answer - this is the answer
           15:14:03.273 9699-9766/com.zainco.library D/AsyncAndAwaitActivity: took 6014*/
////===>another issue leads to null of answer 1 and 2
        /* GlobalScope.launch {
             val time = measureTimeMillis {
                 var answer1: String? = null
                 var answer2: String? = null
                 val job1 = launch {
                     answer1 = doNetworkCall1()
                 }
                 val job2 = launch {
                     answer2 = doNetworkCall2()
                 }
                 //continue immediately with this code
                 Log.d(TAG, "ans 1 $answer1")//will be null cuz every job is a separated coroutine and this log will be printed before doNetworkCall1 is finished 3 secs
                 Log.d(TAG, "ans 2 $answer2")//will be null cuz every job is a separated coroutine and this log will be printed before doNetworkCall2 is finished 3 secs
             }
             Log.d(TAG, "took $time")*/

        //===>to avoid answers are nulls
        /*GlobalScope.launch {
            val time = measureTimeMillis {
                var answer1: String? = null
                var answer2: String? = null
                val job1 = launch {
                    answer1 = doNetworkCall1()
                }
                val job2 = launch {
                    answer2 = doNetworkCall2()
                }
               job1.join()
               job2.join()
                Log.d(TAG, "ans 1 $answer1")
                Log.d(TAG, "ans 2 $answer2")
            }
            Log.d(TAG, "took $time")
        }
        took 3 secs but the way we do that is very terrible(bad practice) instead we can use async*/
//===>use async : return deferred (can be used to get the result of network call or calculation call) not job
        GlobalScope.async /*could be used instead of launch if its value will be used*/{
            val time = measureTimeMillis {
                //whenever we want to do something asynchronously and get the result of that we should use async instead of launch
                val answer1 =
                    async { doNetworkCall1() }//async return type here is a Deferred<String> and the string is the return value of that async block
                val answer2 = async { doNetworkCall2() }
                Log.d(
                    TAG,
                    "ans 1 $answer1"
                )//answer1 here is deferred but use answer1.await which will block this current  coroutine GlobalScope.launch till the answer is available
                Log.d(TAG, "ans 2 $answer2")
            }
            Log.d(TAG, "took $time")// as previous it prints 3 secs  but in a best way
        }
    }

    suspend fun doNetworkCall1(): String {
        delay(3000L)
        return "method take some time to get the answer - this is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "method take some time to get the answer - this is the answer"
    }
}