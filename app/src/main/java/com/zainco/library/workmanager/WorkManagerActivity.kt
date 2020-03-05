package com.zainco.library.workmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.zainco.library.R

class WorkManagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        val constraints = Constraints.Builder().setRequiresCharging(true).build()
        val task = OneTimeWorkRequest.Builder(BackgroundPhotoUpload::class.java)
            .setConstraints(constraints)
            .build()
        val workManager = WorkManager.getInstance()
        workManager.enqueue(task)

    }
}
