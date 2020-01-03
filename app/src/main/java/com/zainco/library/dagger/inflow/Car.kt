package com.zainco.daggerpractice.inflow

import android.util.Log
import javax.inject.Inject


class Car @Inject constructor(val engine: Engine, val wheels: Wheels) {
    private val TAG = "Car"
    fun drive() {
        Log.d(TAG, "driving...")
    }
}