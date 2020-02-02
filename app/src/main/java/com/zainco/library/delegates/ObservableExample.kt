package com.zainco.library.delegates

import kotlin.properties.Delegates

fun main() {
    var observed = false
    var max: Int by Delegates.observable(0) { property, oldValue, newValue ->
        observed = true
    }

    println(max) // 0
    println("observed is ${observed}") // false

    max = 10
    println(max) // 10
    println("observed is ${observed}") // true
}