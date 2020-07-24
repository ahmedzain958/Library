package com.zainco.library.callintercept

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class CallInterceptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_intercept)
//        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE))
    }
}