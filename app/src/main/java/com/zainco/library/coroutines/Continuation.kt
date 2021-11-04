package com.zainco.library.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


var continuation: Continuation<String>? = null

fun main(args: Array<String>) {
    GlobalScope.launch(Dispatchers.Unconfined) {
        println("Suspending")
        val x: String = suspendCoroutine { cont: Continuation<String> ->
            continuation = cont
        }
        println("Resumed!")
    }
    println("After launch")
    continuation!!.resume("Resumed")
    println("After continuation.resume(Unit)")

    suspend fun anotherExample(): String {
        return suspendCoroutine { continuation ->
            val onSuccess: ((String?) -> Unit) = {
                it?.also { data ->
                    continuation.resume(data)
                }
            }
            val onError: ((Throwable) -> Unit) = {
                continuation.resumeWithException(it)
            }
        }
    }

}
