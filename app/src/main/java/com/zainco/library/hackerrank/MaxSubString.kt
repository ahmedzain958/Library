package com.zainco.library.hackerrank

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

}