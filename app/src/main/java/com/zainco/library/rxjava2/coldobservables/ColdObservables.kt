package com.zainco.library.rxjava2.coldobservables

import android.os.Build
import androidx.annotation.RequiresApi
import io.reactivex.Observable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class ColdObservables {
}

@RequiresApi(Build.VERSION_CODES.O)
fun main() {
    val myObservable: Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)
    myObservable.subscribe { item: Long ->
        println("Observer 1: ${item+1} ------" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")))
    }
    Thread.sleep(2000)
    myObservable.subscribe { item: Long ->
        println("Observer 2: ${item+1} ------" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss")))
    }
    Thread.sleep(4000)
    println("---------------------------")
}