package com.zainco.library.coroutines

import android.view.View
import com.zainco.library.R
import kotlinx.android.synthetic.main.fragment_tutorial.*
import kotlinx.coroutines.*
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Ahmed Zain on 10/11/2020.
 */
suspend fun simple(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun main2() = runBlocking<Unit> {
    println("before " + getCurrentDate())
    simple().forEach { value ->
        println("zain inside loop " + getCurrentDate())
        println(value)
    }
    println("after " + getCurrentDate())
}

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat(" HH:mm:ss.SSS")
    return sdf.format(Date())
}

private val coroutineExceptionHandler: CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable ->
        //2
        coroutineScope.launch(Dispatchers.Main) {
        }

        GlobalScope.launch { println("Caught $throwable") }
    }
private val parentJob = Job()
private val coroutineScope =
    CoroutineScope(Dispatchers.Main + parentJob + coroutineExceptionHandler)






fun main() {
    coroutineScope.launch {
        while (true) {
            println("inside coroutine")
            delay(100)
        }
    }
//    coroutineScope.cancel()
}
