package com.zainco.library

fun main() {
    fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /*fun inside fun*/
    }

    foo(1) { println("hello") }     // Uses the default value baz = 1
    foo(qux = { println("hello") }) // Uses both default values bar = 0 and baz = 1
    foo { println("hello") }        // Uses both default values bar = 0 and baz = 1
}