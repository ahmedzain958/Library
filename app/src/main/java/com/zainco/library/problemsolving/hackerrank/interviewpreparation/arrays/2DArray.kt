package com.zainco.library.problemsolving.hackerrank.interviewpreparation.arrays

/**
 * Created by Ahmed Zain on 9/8/2020.
 */
import java.util.*

// Complete the hourglassSum function below.
/*fun hourglassSum(arr: Array<Array<Int>>): Int {
    for (item in arr.indices) {

    }

}*//* val result = hourglassSum(arr)

    println(result)*/

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val arr = Array<Array<Int>>(6, { Array<Int>(6, { 0 }) })

    for (i in 0 until 6) {
        arr[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
    }


}