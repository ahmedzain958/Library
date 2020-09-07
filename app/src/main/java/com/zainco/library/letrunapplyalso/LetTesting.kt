package com.zainco.library.letrunapplyalso

fun main() {
    var x = "Anupam"
    x = x.let { outer ->
        outer.let { inner ->
            println("Inner is $inner and outer is $outer")
            "Kotlin Tutorials Inner let"//affects the value of inner
        }
        "Kotlin Tutorials Outer let"//affects the value of outer
    }
    println(x) //prints Kotlin Tutorials Outer let


//chaining let
    var a = 1
    var b = 2

    a = a
        .let { it + 2 }/*1+2=3*/
        .let {
            val i = it + b/*i=3+2*/
            i/*this is like return i*/
        }
    println(a) //5

    //let for null checks

    var name: String? = "Kotlin let null check"
    name?.let { println(it) } //prints Kotlin let null check
    name = null
    name?.let {
//        println(it)
    } //nothing happens
}