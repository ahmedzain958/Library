package com.zainco.library.workmanager.codelab

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class BlurWorker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}