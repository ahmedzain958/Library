package com.zainco.library.problemsolving.hackerrank.interviewpreparation.arrays

/**
 * Created by Ahmed Zain on 9/9/2020.
 */
import java.util.*

// Complete the minimumBribes function below.
fun minimumBribes(q: IntArray): Unit {
    var bribes = 0
    val size = q.size
    for (i in size - 1 downTo 0) {
        if (q[i] != i + 1) {
            if ((i - 1 >= 0) && (i + 1 == q[i - 1])) {
                bribes++
                swap(q, i - 1, i)
            } else if ((i - 2 >= 0) && (i + 1 == q[i - 2])) {
                bribes += 2
                swap(q, i - 1, i - 2)
                swap(q, i - 1, i)
            } else {
                println("Too Chaotic")
                return
            }
        }
    }
    println(bribes)
}

fun swap(arr: IntArray, value1: Int, value2: Int) {
    val temp: Int = arr[value1]
    arr[value1] = arr[value2]
    arr[value2] = temp
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)
    val t = scan.nextLine().trim().toInt()
    for (tItr in 1..t) {
        val n = scan.nextInt()
        val q = IntArray(n)
        for (i in 0 until n)
            q[i] = scan.nextInt()
        minimumBribes(q)
    }
}
/*this problem can be solved by bubble sort O(n^2) 1<=n<=10^5 very long (1 min 40 sec)
in Hacker rank we will get TLE(Time limit Exceed)*/
