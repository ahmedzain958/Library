package com.zainco.library.lifecycle.casterio.deepdive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(MusicObserver(this))
    }
}
