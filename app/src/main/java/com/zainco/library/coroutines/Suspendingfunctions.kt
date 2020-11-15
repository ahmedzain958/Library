package com.zainco.library.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Created by Ahmed Zain on 10/11/2020.
 */
suspend fun simple(): List<Int> {
    delay(1000) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun main() = runBlocking<Unit> {
    simple().forEach { value -> println(value) }
}