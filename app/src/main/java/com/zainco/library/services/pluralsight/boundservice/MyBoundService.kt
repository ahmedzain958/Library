package com.zainco.library.services.pluralsight.boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyBoundService : Service() {
    inner class MyLocalBinder : Binder() {
        fun getService(): MyBoundService {
            return this@MyBoundService
        }
    }

    override fun onBind(intent: Intent?): IBinder/*bound service onBind() method should never return null*/ {
        return MyLocalBinder()
    }

    fun add(a: Int, b: Int): Int = a + b
    fun sub(a: Int, b: Int): Int = a - b
    fun mul(a: Int, b: Int): Int = a * b
    fun div(a: Int, b: Int): Int = a / b
}