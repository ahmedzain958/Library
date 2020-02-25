package com.zainco.library.rxjava2

import io.reactivex.Single

fun main() {
    Single.just(0)
        .map {
            1 / it
        }
        .onErrorReturn { error: Throwable ->
            if (error is NumberFormatException) 1
            else 0
        }.subscribe { it: Int ->
            println(it)
        }

    Single.just(NumberFormatException())
        .doOnError {
            // handle error
            println("doOnError")
        }
        .onErrorReturn { error: Throwable ->
            // continue emission in case of error also
            return@onErrorReturn NumberFormatException()
        }.subscribe { it: NumberFormatException ->
            println(" onErrorReturn ${it.message}")
        }


    //.doOnError { _refreshing.value = false }
}