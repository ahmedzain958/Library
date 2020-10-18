package com.zainco.library.services.codetutor

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.annotation.Nullable
import com.zainco.library.R
import java.util.*


/**
 * Created by Ahmed Zain on 18/10/2020.
 */
class CodeTutorMyIntentService : IntentService(CodeTutorMyIntentService::class.java.simpleName) {
    private var mRandomNumber = 0
    private var mIsRandomGeneratorOn = false
    private val MIN = 0
    private val MAX = 100
    override fun onHandleIntent(@Nullable intent: Intent?) {
        mIsRandomGeneratorOn = true
        //it automatically creates another worker thread for execution
        startRandomNumberGenerator()
    }

    private fun startRandomNumberGenerator() {
        while (mIsRandomGeneratorOn) {
            try {
                Thread.sleep(1000)
                if (mIsRandomGeneratorOn) {
                    mRandomNumber = Random().nextInt(MAX) + MIN
                    Log.i(
                        getString(R.string.service_demo_tag),
                        "Thread id: " + Thread.currentThread()
                            .id + ", Random Number: " + mRandomNumber
                    )
                }
            } catch (e: InterruptedException) {
                Log.i(getString(R.string.service_demo_tag), "Thread Interrupted")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mIsRandomGeneratorOn = false
        Log.i(
            getString(R.string.string_stopservice),
            "Thread Id: " + Thread.currentThread().id
        )
    }
}