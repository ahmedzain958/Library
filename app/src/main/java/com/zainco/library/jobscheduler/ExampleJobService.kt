package com.zainco.library.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
//this job service runs in the UI thread so we are responsible for starting our own thread and doing our work there
class ExampleJobService : JobService() {
    val TAG = ExampleJobService::class.java.name
    var jobCancelled = false

    /*this method gets called by the system when our job gets cancelled , for example if we wanted to execute our job when we have WIFI connection
    * and the user turns off WIFI*/
    override fun onStopJob(params: JobParameters?): Boolean {
        Log.d(TAG, "job cancelled before completion")
        jobCancelled = true
        return false//true if we want to reschedule the job or retry it later, false if your work isn't important
        //when the job is cancelled the wake lock isn't kept for our app anymore
    }

    private fun doBackgroundWork(params: JobParameters?) {
        Thread(Runnable {
            for (i in 1..10) {
                Log.d(TAG, "run $i")
                if (jobCancelled) return@Runnable
                Thread.sleep(100)
            }
            Log.d(TAG, "job finished")
            /*we are resp. for telling the system that our job is finished otherwise it will keep the wake lock on the device on behalf of
            * our app which means that the user will see in the settings that the app is eating the battery */
            jobFinished(params, false/*we don't want to reschedule the job*/)
        }).start()

    }

    /*
       * if this task is short and can be executed in the scope of this method we have to return false
       * this tells the system that out job is over when this method is over*/
    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d(TAG, "Job started")
        doBackgroundWork(params)
        return true //for long running operation
    }
}