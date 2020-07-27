package com.zainco.library.runningtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import com.zainco.library.runningtracker.db.RunDAO
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/*in order to inject something like db in android component class with hilt in activity, service, fragment
* u will use @Inject and @AndroidEntryPoint*/
@AndroidEntryPoint
class MvvmRunningTrackerActivity : AppCompatActivity() {
    @Inject
    lateinit var runDAO: RunDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm_running_tracker)
    }
}