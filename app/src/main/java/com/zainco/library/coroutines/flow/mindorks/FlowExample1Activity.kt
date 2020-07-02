package com.zainco.library.coroutines.flow.mindorks

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_flow_example1.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FlowExample1Activity : AppCompatActivity() {
    private val TAG = FlowExample1Activity::class.java.name

    @InternalCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow_example1)
        CoroutineScope(Dispatchers.Main).launch {
            flow {
                Log.d(TAG, "Start flow")
                (0..10).forEach {
                    // Emit items with 500 milliseconds delay
                    delay(500)
                    Log.d(TAG, "Emitting $it")
                    emit(it)
                }
            }.map {
                it * it
            }.flowOn(Dispatchers.Default).collect {
                textView13.text = it.toString()
            }

        }
    }
}