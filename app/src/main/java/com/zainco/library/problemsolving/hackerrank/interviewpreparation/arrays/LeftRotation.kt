package com.zainco.library.problemsolving.hackerrank.interviewpreparation.arrays

/**
 * Created by Ahmed Zain on 9/9/2020.
 */
import java.util.*

// Complete the rotLeft function below.
fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
    val arrayLength = a.size
    for (j in 1..d) {
        val firstElement = a[0]
        for (i in 0 until arrayLength) {
            if (i < arrayLength - 1) {
                a[i] = a[i + 1]
            } else a[arrayLength - 1] = firstElement
        }
    }
    return a
}

fun main() {
    val scan = Scanner(System.`in`)

    val nd = scan.nextLine().split(" ")

    val n = nd[0].trim().toInt()

    val d = nd[1].trim().toInt()

    val a = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = rotLeft(a, d)

    println(result.joinToString(" "))
}
