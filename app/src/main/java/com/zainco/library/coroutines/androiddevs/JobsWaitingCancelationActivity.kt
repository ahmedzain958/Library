package com.zainco.library.coroutines.androiddevs

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.coroutines.*

class JobsWaitingCancelationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_waiting_cancelation)
        //video (6) - example 1
        /*val job = GlobalScope.launch(Dispatchers.Default) {// every GlobalScope.launch returns a job
            repeat(5) {
                Log.d("JobsWaitingCancelation", "coroutine is still working")
                delay(1000)
            }
        }
        runBlocking {
            job.join()//job.join() starts executing job and can't be called except from inside a coroutines scope
            //will block main thread (cuz inside runBlocking) until the upper coroutine(job) is finished
            Log.d("JobsWaitingCancelation", "main thread is continuing")//this line in the mainthread won't printed until the job is done (5s)
        }
        020-06-22 10:34:33.105 13950-14041/com.zainco.library D/JobsWaitingCancelation: coroutine is still working
        2020-06-22 10:34:34.117 13950-14041/com.zainco.library D/JobsWaitingCancelation: coroutine is still working
        2020-06-22 10:34:36.123 13950-14041/com.zainco.library D/JobsWaitingCancelation: coroutine is still working
        2020-06-22 10:34:37.126 13950-14041/com.zainco.library D/JobsWaitingCancelation: coroutine is still working
        2020-06-22 10:34:38.134 13950-13950/com.zainco.library D/JobsWaitingCancelation: main thread is continuing*/
        //video (6) - example 2 cancel job, if we run the next code in the oncreate() it starts executing the job with delaying 5 secs but it won't continue till
        // the end of the 5s just the 2s of the next runBlocking { delay(2000)   job.cancel()     Log.d("JobsWaitingCancelation", "main thread is continuing")
// then the job will be cancelled
        /*    val job = GlobalScope.launch(Dispatchers.Default) {
                repeat(5) {
                    Log.d("JobsWaitingCancelation", "coroutine is still working")
                    delay(1000)
                }
            }
            runBlocking {
                delay(2000)
                job.cancel()
                Log.d("JobsWaitingCancelation", "main thread is continuing")
            }
            020-06-22 10:34:33.105 13950-14041/com.zainco.library D/JobsWaitingCancelation: coroutine is still working
            2020-06-22 10:34:34.117 13950-14041/com.zainco.library D/JobsWaitingCancelation: coroutine is still working
            2020-06-22 10:34:38.134 13950-13950/com.zainco.library D/JobsWaitingCancelation: main thread is continuing*/
        //video (6) - example 3 fibonacci
        /*val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d("JobsWaitingCancelation", "starting long runing calc.")
            for (i in 30..40) {
                    Log.d("JobsWaitingCancelation", "result for i=$i : ${fib(i)}")// inside this for loop the coroutines is very busy by calculations
                     //and there is not time to check for cancellation
            }
            Log.d("JobsWaitingCancelation", "ending long runing calc.")
        }
        runBlocking {
            delay(2000)//fib should take 2 secs inside the loop to finish
            job.cancel()
            Log.d("JobsWaitingCancelation", "canceled job")
        }
        2020 - 06-22 10:47:12.535 16163-16255/com.zainco.library D/JobsWaitingCancelation: starting long runing calc.
        2020 - 06-22 10:47:12.591 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 30 : 832040
        2020 - 06-22 10:47:12.680 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 31 : 1346269
        2020 - 06-22 10:47:12.825 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 32 : 2178309
        2020 - 06-22 10:47:13.058 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 33 : 3524578
        2020 - 06-22 10:47:13.430 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 34 : 5702887
        2020 - 06-22 10:47:14.033 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 35 : 9227465
        2020 - 06-22 10:47:14.536 16163-16163/com.zainco.library D/JobsWaitingCancelation: canceled job
        2020 - 06-22 10:47:15.022 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 36 : 14930352
        2020 - 06-22 10:47:16.596 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 37 : 24157817
        2020 - 06-22 10:47:19.131 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 38 : 39088169
        2020 - 06-22 10:47:23.243 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 39 : 63245986
        2020 - 06-22 10:47:29.887 16163-16255/com.zainco.library D/JobsWaitingCancelation: result for i = 40 : 102334155
        2020 - 06-22 10:47:29.887 16163-16255/com.zainco.library D/JobsWaitingCancelation: ending long runing calc.*/
//isActive
        /*   val job = GlobalScope.launch(Dispatchers.Default) {
               Log.d("JobsWaitingCancelation", "starting long runing calc.")
               for (i in 30..40) {
                   //here for ex after i=35 the job hasn't been successfully cancelled because the coroutine is so busy with calculations
                   // and bec. it has no suspend func. to be paused , there is no pause that can tell coroutine it has been cancelled, so we have to check
                   //manually if coroutine has been cancelled or not to cancel it properly
                   if (isActive) {// This property is a shortcut for `coroutineContext.isActive` in the scope when[CoroutineScope] is available
                       Log.d("JobsWaitingCancelation", "result for i=$i : ${fib(i)}")
                   }
               }
               Log.d("JobsWaitingCancelation", "ending long runing calc.")
           }
           runBlocking {
               delay(2000)
               job.cancel()
               Log.d("JobsWaitingCancelation", "canceled job")
           }
           2020 - 06-22 10:56:42.033 18353-18460/com.zainco.library D/JobsWaitingCancelation: starting long runing calc.
           2020 - 06-22 10:56:42.101 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 30 : 832040
           2020 - 06-22 10:56:42.189 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 31 : 1346269
           2020 - 06-22 10:56:42.333 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 32 : 2178309
           2020 - 06-22 10:56:42.562 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 33 : 3524578
           2020 - 06-22 10:56:42.932 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 34 : 5702887
           2020 - 06-22 10:56:43.533 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 35 : 9227465
           2020 - 06-22 10:56:44.044 18353-18353/com.zainco.library D/JobsWaitingCancelation: canceled job
           2020 - 06-22 10:56:44.519 18353-18460/com.zainco.library D/JobsWaitingCancelation: result for i = 36 : 14930352
           2020 - 06-22 10:56:44.520 18353-18460/com.zainco.library D/JobsWaitingCancelation: ending long runing calc.*/
        //withTimeout
        val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d("JobsWaitingCancelation", "starting long runing calc.")
            withTimeout(3000) {// determines only 3 secs to do its calculations then cancel automatically within with no need to define another coroutine and delay(3000) then cancel
                for (i in 30..40) {
                    //here for ex after i=35 the job hasn't been successfully cancelled because the coroutine is so busy with calculations
                    // and bec. it has no suspend func. to be paused , there is no pause that can tell coroutine it has been cancelled, so we have to check
                    //manually if coroutine has been cancelled or not to cancel it properly
                    if (isActive) {//  isActive is useful because  withTimeout(3000) is exactly like calling the job in runBlocking{ delay(3000) job.join()}
                        Log.d("JobsWaitingCancelation", "result for i=$i : ${fib(i)}")
                    }
                }
            }
            Log.d("JobsWaitingCancelation", "ending long runing calc.")
        }
        /*2020-10-25 13:53:41.246 28112-28484/com.zainco.library D/JobsWaitingCancelation: starting long runing calc.
 2020-10-25 13:53:41.339 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=30 : 832040
 2020-10-25 13:53:41.431 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=31 : 1346269
 2020-10-25 13:53:41.577 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=32 : 2178309
 2020-10-25 13:53:41.810 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=33 : 3524578
 2020-10-25 13:53:42.183 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=34 : 5702887
 2020-10-25 13:53:42.787 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=35 : 9227465
 2020-10-25 13:53:43.759 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=36 : 14930352
 2020-10-25 13:53:45.325 28112-28484/com.zainco.library D/JobsWaitingCancelation: result for i=37 : 24157817
 2020-10-25 13:53:45.326 28112-28484/com.zainco.library D/JobsWaitingCancelation: ending long runing calc.*/
    }

    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }
}