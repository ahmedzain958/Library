package com.zainco.library

fun maxSubString(str: String): String {
    if (str.isNotEmpty()) {
        var max = ""
        for (i in str.indices) {
            if (max <= str.substring(i)) {
                max = str.substring(i)
            }
        }
        return max
    }
    return ""
}

fun main() {
    println(maxSubString("ahmed"))
    /*    val list = listOf(Pharmacy("Ahmed"), Pharmacy("Amgad"), Pharmacy("Zain"))
    var list2: List<Pharmacy> = list.filter {
        it.name.startsWith("A")
    }
    println(list)
    println(list2)*/
}


data class Pharmacy(
    val name: String
)
