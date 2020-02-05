package com.zainco.library.archcomponents.livedata.livedatatransformations.mediator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_mediator_live_data.*

class MediatorLiveDataActivity : AppCompatActivity() {
    //this observer listens to any change that takes place in either id1 or id2 that exist in the viewmodel
    private val changeObserver = Observer<String> { value: String ->
        textView2.text = value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mediator_live_data)
        val mediatorLiveDataViewModel =
            ViewModelProviders.of(this).get(MediatorLiveDataViewModel::class.java)
        mediatorLiveDataViewModel.mediatorLiveData.observe(this, changeObserver)
        button.setOnClickListener {
            mediatorLiveDataViewModel.incId1()
        }
        button2.setOnClickListener {
            mediatorLiveDataViewModel.incId2()
        }
//for testing mutablelivedata vs livedata
        mediatorLiveDataViewModel.someLiveData.observe(this, Observer {
            Log.d("tessst", it.toString())
        })
        (mediatorLiveDataViewModel.someLiveData as? MutableLiveData)?.value = "Zain"
    }
}
