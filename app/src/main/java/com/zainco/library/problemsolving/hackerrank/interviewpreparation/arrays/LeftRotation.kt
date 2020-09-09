package com.zainco.library.problemsolving.hackerrank.interviewpreparation.arrays

/**
 * Created by Ahmed Zain on 9/9/2020.
 */
import java.util.*

// Complete the rotLeft function below.
fun rotLeft(a: Array<Int>, d: Int): IntArray {
    val arrayLength = a.size
    val rotatedArray = IntArray(arrayLength)
    var rotatedIndex = d
    var i = 0
    while (rotatedIndex < arrayLength) {//while shifted index still doesn't reach the end of the array
        rotatedArray[i] = a[rotatedIndex]
        i++
        rotatedIndex++
    }
    rotatedIndex = 0
    while (rotatedIndex < d) {
        rotatedArray[i] = a[rotatedIndex]
        i++
        rotatedIndex++
    }
    return rotatedArray
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
