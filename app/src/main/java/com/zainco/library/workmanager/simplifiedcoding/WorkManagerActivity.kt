package com.zainco.library.workmanager.simplifiedcoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_work_manager.*

class WorkManagerActivity : AppCompatActivity() {
    companion object {
        const val KEY_TASK_DESC = "key_task_desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager)
        //the data sent to the worker
        val data = Data.Builder()
            .putString(
                KEY_TASK_DESC,
                "Hey I am sending the work data"
            )
            .build()

        //the constraints
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
/*request takes the data and the constraints
* here, you can define different requests with different data and constraints*/
        val oneTimeWorkRequest = OneTimeWorkRequest
            .Builder(MyWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .build()

        button5.setOnClickListener {
            //starts the work manager
            WorkManager.getInstance().enqueue(oneTimeWorkRequest)
        }
        //listens to the work manager
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, Observer { workInfo ->
                if (workInfo != null) {
                    if (workInfo.state.isFinished) {
                        val output = workInfo.outputData.getString(MyWorker.KEY_TASK_OUTPUT)
                        textView5.append(output + "\n")
                    }
                    val status = workInfo.state.name
                    textView5.append(status + "\n")
                }
            })
    }
}
