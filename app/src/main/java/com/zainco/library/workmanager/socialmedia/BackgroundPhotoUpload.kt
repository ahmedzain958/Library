package com.zainco.library.workmanager.socialmedia

import android.content.Context
import android.util.Log.d
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

class BackgroundPhotoUpload(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        d("ZBackgroundPhotoUpload", "message")
        return success()
    }

}