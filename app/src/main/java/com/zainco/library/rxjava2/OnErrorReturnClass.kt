package com.zainco.library.rxjava2

import android.annotation.SuppressLint
import io.reactivex.Single

fun main() {
    Single.just(0)
        .map {
            1/it
        }
        .onErrorReturn { error: Throwable ->
            if (error is NumberFormatException) 1
            else 0
        }.subscribe { it: Int ->
            println(it)
        }
}