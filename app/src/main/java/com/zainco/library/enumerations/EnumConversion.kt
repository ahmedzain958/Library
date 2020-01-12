package com.zainco.library.enumerations

fun main() {
    val x: TransactionType = "CREDIT".enumOf()
    val x2: TransactionType = "DEBIT".toEnum()
    println("CREDIT $x")
    println("DEBIT $x2")
}

enum class TransactionType {
    CREDIT, DEBIT
}

inline fun <reified T : Enum<T>> String.enumOf(): T = enumValueOf(this)
inline fun String.toEnum() = TransactionType.valueOf(this)