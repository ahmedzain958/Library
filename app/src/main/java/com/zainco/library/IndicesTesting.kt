package com.zainco.library

fun main() {
    val empty = emptyList<Any>()
    println("empty.indices.isEmpty() is ${empty.indices.isEmpty()}") // true
    val collection = listOf('a', 'b', 'c')
    println(collection.indices) // 0..2
}