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
/*
* If you are an Android developer and looking to build an app asynchronously you might be using RxJava as it has an operator for almost everything. RxJava has become one of the most important things to know in Android.

But with Kotlin a lot of people tend to use Co-routines. With Kotlin Coroutine 1.2.0 alpha release Jetbrains came up with Flow API as part of it. With Flow in Kotlin now you can handle a stream of data that emits values sequentially.

In Kotlin, Coroutine is just the scheduler part of RxJava but now with Flow APIs coming along side it, it can be alternative to RxJava in Android
In this blog, we will see how Flow APIs work in Kotlin and how can we start using it in our android projects. We will cover the following topics,

What is Flow APIs in Kotlin Coroutines?
Start Integrating Flow APIs in your project
Builders in Flows
Few examples using Flow Operators.
Let's discuss them one by one.
* What is Flow APIs in Kotlin Coroutines?
Flow API in Kotlin is a better way to handle the stream of data asynchronously that executes sequentially.

So, in RxJava, Observables type is an example of a structure that represents a stream of items. Its body does not get executed until it is subscribed to by a subscriber.
*  and once it is subscribed, subscriber starts getting the data items emitted. Similarly,
* Flow works on the same condition where the code inside a flow builder does not run until the flow is collected.*/
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