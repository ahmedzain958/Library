package com.zainco.library

fun main() {

    val list = listOf(Pharmacy("Ahmed"), Pharmacy("Amgad"), Pharmacy("Zain"))
    var list2: List<Pharmacy> = list.filter {
        it.name.startsWith("A")
    }
    println(list)
    println(list2)
}

data class Pharmacy(
    val name: String
)
