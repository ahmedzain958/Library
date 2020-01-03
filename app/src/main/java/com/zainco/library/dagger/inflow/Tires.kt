package com.zainco.daggerpractice.inflow

import android.util.Log

class Tires constructor(){
    private val TAG = "Car"

    fun inflate() {
        Log.d(TAG, "Tires inflated")
    }
}