package com.zainco.library.services.codetutor

import android.app.Service

import android.content.Intent
import android.os.*

import android.util.Log
import androidx.annotation.Nullable
import com.zainco.library.R
import java.util.*


/**
 * Created by Ahmed Zain on 18/10/2020.
 */
class MyService : Service() {
    private var mRandomNumber = 0
    private var mIsRandomGeneratorOn = false
    private val MIN = 0
    private val MAX = 100

    //this class generates Random no from 0-100
    private inner class RandomNumberRequestHandler : Handler() {
        override fun handleMessage(msg: Message) {
            Log.i(getString(R.string.service_demo_tag), "Message intercepted")
            when (msg.what) {
                GET_COUNT -> {
                    val messageSendRandomNumber: Message = Message.obtain(null, GET_COUNT)
                    messageSendRandomNumber.arg1 = getRandomNumber()
                    try {
                        Log.i(
                            getString(R.string.service_demo_tag),
                            "Replaying with random number to requester"
                        )
                        msg.replyTo.send(messageSendRandomNumber)
                    } catch (e: RemoteException) {
                        Log.i(getString(R.string.service_demo_tag), "" + e.message)
                    }
                }
            }
            super.handleMessage(msg)
        }
    }

    internal inner class MyServiceBinder : Binder() {
        val service: MyService
            get() = this@MyService
    }

    private val mBinder: IBinder = MyServiceBinder()
    private val randomNumberMessenger: Messenger = Messenger(RandomNumberRequestHandler())

    @Nullable
    override fun onBind(intent: Intent): IBinder {
        Log.i(getString(R.string.service_demo_tag), "In OnBind")
        return if (intent.getPackage() === "serviceclientapp.youtube.com.messengerserviceclientapp") {
            randomNumberMessenger.binder
        } else {
            mBinder
        }
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i(getString(R.string.service_demo_tag), "In OnReBind")
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Log.i(getString(R.string.service_demo_tag), "Service Started")
    }

    override fun onDestroy() {
        super.onDestroy()
        stopRandomNumberGenerator()
        Log.i(getString(R.string.service_demo_tag), "Service Destroyed")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(
            getString(R.string.service_demo_tag),
            "In onStartCommend, thread id: " + Thread.currentThread().id
        )
        mIsRandomGeneratorOn = true
        Thread(Runnable { startRandomNumberGenerator() }).start()
        return START_STICKY
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

    private fun stopRandomNumberGenerator() {
        mIsRandomGeneratorOn = false
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(getString(R.string.service_demo_tag), "In onUnbind")
        return super.onUnbind(intent)
    }

    fun getRandomNumber(): Int {
        return mRandomNumber
    }

    companion object {
        const val GET_COUNT = 0
    }
}