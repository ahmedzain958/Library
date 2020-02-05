package com.zainco.library.archcomponents.textmutablevslivedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class SomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this)[MyViewModel::class.java]
        viewModel.someLiveData.observe(this, Observer {
            Log.d("tessst", it.toString())
        })
        (viewModel.someLiveData as? MutableLiveData)?.value = "Zain"

    }
}