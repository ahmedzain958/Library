package com.zainco.library.coroutines.androiddevs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.coroutines.*
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
                   //answer 1 and 2 won't be null cuz will wait till network call finish then assign values and cuz both called in same coroutines and took 6 secs unlike the next ex.
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
               job1.join()//every job blocks GlobalScope.launch and run separately but it isn't the best way
               job2.join()
                Log.d(TAG, "ans 1 $answer1")
                Log.d(TAG, "ans 2 $answer2")
            }
            Log.d(TAG, "took $time")
        }
        took 3 secs but the way we do that is very terrible(bad practice) instead we can use async*/
//===>use async : return deferred (can be used to get the result of network call or long calculation call) not job
        GlobalScope.launch /*.async could be used instead of launch if its value will be used*/{
            val time = measureTimeMillis {
                //whenever we want to do something asynchronously and get the result of that we should use async instead of launch
                val answer1: Deferred<String> =
                    async { doNetworkCall1() }//async return type here is a Deferred<String> and the string is the return value of that async block
                val answer2: Deferred<String> = async { doNetworkCall2() }
                Log.d(
                    TAG,
                    "ans 1 ${answer1.await()}"
                )//answer1 here is deferred but use answer1.await which will block this current  coroutine (GlobalScope.launch/GlobalScope.async) till the answer is av'ailable
                Log.d(TAG, "ans 2 ${answer2.await()}")
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
    /*
    * Launch vs Async in Kotlin Coroutines
The difference is that the launch{} does not return anything and the async{}returns an instance of Deferred<T>,
* which has an await()function that returns the result of the coroutine
* In other words:
launch: fire and forget
async: perform a task and return a result
* ex:
* We have a function fetchUserAndSaveInDatabase like below:

suspend fun fetchUserAndSaveInDatabase() {
    // fetch user from network
    // save user in database
    // and do not return anything
}
Now, we can use the launch like below:

GlobalScope.launch(Dispatchers.Main) {
    fetchUserAndSaveInDatabase() // do on IO thread
    * As the fetchUserAndSaveInDatabase does not return anything,
    * we can use the launch to complete that task and then do something on Main Thread.
    * But when we need the result back, we need to use the async.
}*/

/*another mindorks example
* We have two functions which return User like below:

suspend fun fetchFirstUser(): User {
    // make network call
    // return user
}

suspend fun fetchSecondUser(): User {
    // make network call
    // return user
}
Now, we can use the async like below:

GlobalScope.launch(Dispatchers.Main) {
    val userOne = async(Dispatchers.IO) { fetchFirstUser() }
    val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
    showUsers(userOne.await(), userTwo.await()) // back on UI thread
}
* Here, it makes both the network call in parallel, await for the results, and then calls the showUsers function.*/
}