package com.zainco.library.coroutines.androiddevs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

//global scope lives as long as application does however most of time it will be bad practice
class LifecyclescopeViewmodelscopeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecyclescope_viewmodelscope)
    }
}