package com.zainco.library.archcomponents.livedata.livedatatransformations.mediator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_mediator_live_data.*

class MediatorLiveDataActivity : AppCompatActivity() {
    var c1 = 0
    var c2 = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator_live_data)
        button.setOnClickListener {
            c1.inc()
        }
        button2.setOnClickListener {
            c2.inc()
        }

        val mediatorLiveDataViewModel =
            ViewModelProviders.of(this).get(MediatorLiveDataViewModel::class.java)
        mediatorLiveDataViewModel.setId1(c1)
        mediatorLiveDataViewModel.getId1().observe(this, Observer {
            textView2.text = it.toString()
        })
        mediatorLiveDataViewModel.getId2().observe(this, Observer {
            textView3.text = it.toString()
        })
    }
}
