package com.zainco.library.coroutines.androiddevs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.coroutines.delay

/*
* Dispatchers: Dispatchers help coroutines in deciding the thread on which the work has to be done. There are majorly three types of Dispatchers which are as IO, Default, and Main.
* IO dispatcher is used to do the network and disk-related work. Default is used to do the CPU intensive work. Main is the UI thread of Android. In order to use these,
* we need to wrap the work under the async function. Async function looks like below.
suspend: Suspend function is a function that could be started, paused, and resume.*/
/*
* difference between thread.sleep() and coroutines delay() method :
* if we have 2 construction projects(2 threads) and every project has workers(worker = coroutine)
* delay: a worker paused his work from one of the cons. projects
* thread.sleep():project stopped(thread stopped) working so all workers(coroutines) should stop working
* */
//delay functions (suspend functions) called only from inside coroutine blocks or from inside another suspend function
/*ex
* So, the fetchAndShowUser can only be called from another suspend function or a coroutine.
*  We can't make the onCreate function of an activity suspend, so we need to call it from the coroutines like below:*/
class CoroutinesActivity : AppCompatActivity() {
    val TAG = "CoroutinesActivitys"

    //coroutines will be cancelled when main thread finishes
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        /*videos till 1-3

        //GlobalScope means that this coroutine will live as long as app does
        GlobalScope.launch {
            //put instr. ur coroutines will execute
            delay(1000L)// works different from thread.sleep() because it pauses the current coroutine and won't block the whole thread (main) it is working in
            d(TAG, "coroutines says hello from ${Thread.currentThread().name}")
        }
        d(TAG, "hello from ${Thread.currentThread().name}")

        GlobalScope.launch {//both answer will display after 6 seconds
            val doNetworkCall = doNetworkCall()//this will influence the second delay because doNetworkCall isn't a coroutine scope but it is just a suspend function
            val doNetworkCall2 = doNetworkCall2()//will be affected by the first delay
            d(TAG, doNetworkCall)
            d(TAG, doNetworkCall2)
        }

        //Coroutines run in specific contexts; contexts will describe in which thread our coroutine will be started in
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
        }
        //video 4
        GlobalScope.launch(Dispatchers.IO) {
            val answer = doNetworkCall()//this is ex makes a network call for 3 secs
            withContext(Dispatchers.Main) {
                //executed in the main thread
                textView8.text = answer
            }
        }
*/
/*
 withContext is nothing but another way of writing the async where we do not have to write await() / it is just  asuspend function transforms the dispatcher you are in

suspend fun fetchUser(): User {
    return withContext(Dispatchers.IO) {
        // make network call
        // return user
    }
}
* Now, let's use withContext in our async example of fetchFirstUser and fetchSecondUser in parallel.

GlobalScope.launch(Dispatchers.Main) {
    val userOne = withContext(Dispatchers.IO) { fetchFirstUser() }
    val userTwo = withContext(Dispatchers.IO) { fetchSecondUser() }
    showUsers(userOne, userTwo) // back on UI thread
}
 When we use withContext, it will run in series instead of parallel. That is a major difference
*/
/*
        //runBlocking gives you  coroutines scope in which we can execute a suspend function
        // video 5 :runBlocking starts coroutines in the main thread and also its delay method blocks the thread unlike delay inside global scope
        runBlocking {
            //actually blocks the main thread
            delay(100L)
            delay(100L)
        }
        // delay here in this fn doesn't block, we can still use our ui
        GlobalScope.launch(Dispatchers.Main)
        {
            delay(100L)
        }*/

        /*global scope example
          Log.d(TAG, "before Globalscope.launch")
          GlobalScope.launch(Dispatchers.Main) {
              Log.d(TAG, "start Globalscope.launch")
              delay(3000L)
              Log.d(TAG, "end Globalscope.launch")
          }
          Log.d(TAG, "after Globalscope.launch")
          prints the following
           2020-05-27 22:55:57.556 21493-21493/com.zainco.library D/CoroutinesActivitys: before Globalscope.launch
           2020-05-27 22:55:58.378 21493-21493/com.zainco.library D/CoroutinesActivitys: after Globalscope.launch
           //the upper 2 lines means that Globalscope {} block doesn't block the main thread although it has a delay(3000)
           2020-05-27 22:55:58.581 21493-21493/com.zainco.library D/CoroutinesActivitys: start Globalscope.launch
           2020-05-27 22:56:01.584 21493-21493/com.zainco.library D/CoroutinesActivitys: end Globalscope.launch*/

/*the next few lines
            Log.d(TAG, "before Thread.sleep")
            Log.d(TAG, "start  Thread.sleep")
            Thread.sleep(3000L)
            Log.d(TAG, "end  Thread.sleep")
        Log.d(TAG, "after  Thread.sleep")

         are equal to*/
        /*Log.d(TAG, "before runblocking")
        // if you have a suspend function and you want to call it you can call it from inside runBlocking , but if you don't care about it is not asynchronous
        runBlocking {
            Log.d(TAG, "start  runblocking")
            delay(3000L)//this is like calling delay() method inside oncreate() (mean inside main thread) but it can't be done bec. it won't be inside coroutines
            Log.d(TAG, "end  runblocking")
        }
        Log.d(TAG, "after  runblocking")*/

//video 5 runBlocking
/*  Log.d(TAG, "before  runblocking")
 runBlocking {
     launch(Dispatchers.IO) {//no need for Globalscope keyword bec. u are already inside a coroutine
         delay(3000L)// doesn't block the io thread
         Log.d(TAG, "finished  IO coroutine 1")
     }
     launch(Dispatchers.IO) {//no need for Globalscope keyword bec. u are already inside a coroutine
         delay(3000L)
         Log.d(TAG, "finished  IO coroutine 2")// will be executed after 3 secs not 6
     }
     Log.d(TAG, "start  runblocking")
     delay(5000L)//this is like calling delay() method inside oncreate() (mean inside main thread) but it can't be done bec. it won't be inside coroutines
     Log.d(TAG, "end  runblocking")
 }
 Log.d(TAG, "after  runblocking")

2020-06-22 03:02:51.783 628-628/com.zainco.library D/CoroutinesActivitys: before  runblocking
2020-02    03:02:51.885 628-628/com.zainco.library D/CoroutinesActivitys: start  runblocking
2020-06-22 03:02:54.893 628-697/com.zainco.library D/CoroutinesActivitys: finished  IO coroutine 1
2020-06-22 03:02:54.896 628-697/com.zainco.library D/CoroutinesActivitys: finished  IO coroutine 2
2020-06-22 03:02:56.889 628-628/com.zainco.library D/CoroutinesActivitys: end  runblocking//after waiting the 5 secs
2020-06-22 03:02:56.894 628-628/com.zainco.library D/CoroutinesActivitys: after  runblocking
*/
    }

    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "method doNetworkCall take some time to get the answer - this is the answer"
    }

    suspend fun doNetworkCall2(): String {
        delay(3000L)
        return "method doNetworkCall2 take some time to get the answer - this is the answer"
    }
}
/*Mindorks
* Use withContext when you do not need the parallel execution.
Use async only when you need the parallel execution.
Both withContext and async can be used to get the result which is not possible with the launch.
Use withContext to return the result of a single task.
Use async for results from multiple tasks that run in parallel.*/