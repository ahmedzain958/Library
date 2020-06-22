package com.zainco.library.threading

import android.os.Build
import androidx.annotation.RequiresApi
import com.zainco.library.dates.DateTimeClasses

/**
 * Created by Ahmed Zain on 6/22/2020.
 */
internal class ExampleThread : Thread() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun run() {
        try {
            sleep(2000)
            println("after 2000 at sec ${DateTimeClasses.displayCurrentSecond()}")
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}

fun main(args: Array<String>) {
    ExampleThread()
        .start()

    ExampleThread()
        .start()

}