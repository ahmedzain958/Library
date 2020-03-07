package com.zainco.library

fun main(args: Array<String>) {

    val myMap = mapOf<Int, String>(1 to "Ajay", 4 to "Vijay", 3 to "Prakash")
    for (key in myMap.keys) {
        println(myMap[key])
    }
}