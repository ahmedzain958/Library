package com.zainco.library.coroutines.flowsVsSuspend

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    zz()
}
fun zz() = runBlocking {
    val startTime = System.currentTimeMillis()

    demoSuspendFun().forEach {
        println("receive $it after ${System.currentTimeMillis() - startTime}ms")
    }
}
suspend fun demoSuspendFun(): List<Int> {
    val result = mutableListOf<Int>()
    result.addAll(longTask1())
    result.addAll(longTask2())
    result.addAll(longTask3())
    return result
}
suspend fun longTask1(): List<Int> {
    delay(1000)
    return listOf(1, 2)
}

suspend fun longTask2(): List<Int> {
    delay(1000)
    return listOf(3, 4)
}

suspend fun longTask3(): List<Int> {
    delay(1000)
    return listOf(5, 6)
}