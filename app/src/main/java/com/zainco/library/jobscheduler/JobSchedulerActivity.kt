package com.zainco.library.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

/*here under what circumstances we want to start the jobservice*/
class JobSchedulerActivity : AppCompatActivity() {
    val TAG = JobSchedulerActivity::class.java.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_scheduler)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun scheduleJob(view: View) {
        val componentName = ComponentName(this, ExampleJobService::class.java)
        val info = JobInfo.Builder(123/*later we can identify this job*/, componentName)
            .setRequiresCharging(true)//will only execute our job when the device is charging
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)//we execute this when we have WIFI connection
            .setPersisted(true/*keep our job alive even if we rebooted the device*/)
            /*no ultimate control when exactly will it be executed, it is only guaranteed that it will be executed within this period
            * because the system tries to be intelligent about executing these jobs to save battery and memory
            * can't be lower than 15 min and if you tried to set this lower than 15 min it will be set to 15 min internally*/
            .setPeriodic(15 * 60 * 1000)/*defines how often we want to execute this job*/
            .build()
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val resultCode = scheduler.schedule(info)
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "job scheduled")
        } else {
            Log.d(TAG, "job scheduling failed")
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cancelJob(view: View) {
        val scheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        scheduler.cancel(123)
        Log.d(TAG, "job cancelled")
    }
}
