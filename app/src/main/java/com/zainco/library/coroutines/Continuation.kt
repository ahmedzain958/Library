package com.zainco.library.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


var continuation: Continuation<Unit>? = null

fun main(args: Array<String>) {
    GlobalScope.launch(Dispatchers.Unconfined) {
        println("Suspending")
        suspendCoroutine<Unit> { cont: Continuation<Unit> ->
            continuation = cont
        }
        println("Resumed!")
    }
    println("After launch")
    continuation!!.resume(Unit)
    println("After continuation.resume(Unit)")
}
