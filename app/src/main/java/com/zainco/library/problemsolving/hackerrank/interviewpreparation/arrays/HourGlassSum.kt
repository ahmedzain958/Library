package com.zainco.library.problemsolving.hackerrank.interviewpreparation.arrays

/**
 * Created by Ahmed Zain on 9/9/2020.
 */
import java.util.*
import kotlin.math.max

// Complete the hourglassSum function below.
fun hourglassSum(arr: Array<Array<Int>>): Int {
    var max = Int.MIN_VALUE
    val columns = arr[0].size
    val rows = arr.size
    for (i in 0 until rows - 2) {
        for (j in 0 until columns - 2) {
            val currentSum =
                arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1] + arr[i + 2][j] +
                        arr[i + 2][j + 1] + arr[i + 2][j + 2]
            max = max(currentSum, max)
        }
    }
    return max
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val arr = Array<Array<Int>>(6, { Array<Int>(6, { 0 }) })

    for (i in 0 until 6) {
        arr[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
    }

    val result = hourglassSum(arr)

    println(result)
}
