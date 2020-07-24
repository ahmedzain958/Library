package com.zainco.library.problemsolving.hackerrank.interviewpreparation.warmup

/**
 * Created by Ahmed Zain on 7/3/2020.
 */
import java.util.*

/* example url
* https://www.hackerrank.com/challenges/jumping-on-the-clouds/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
* */
// Complete the jumpingOnClouds function below.
fun jumpingOnClouds(cloudsArray: Array<Int>, cloudsNumber: Int): Int {
    var jumpedSteps = 0
    var counter = 0
    while (counter + 1 < cloudsNumber) {
        if (cloudsArray[counter] == 0) {
            if (counter + 1 < cloudsNumber) {
                val nextNextItem = cloudsArray[counter + 2]
                val nextItem = cloudsArray[counter + 1]
                if (nextNextItem != 1) {
                    counter += 2
                    if (counter >= cloudsNumber)
                        break
                    jumpedSteps++
                    continue
                } else if (nextItem != 1) {
                    jumpedSteps++
                    counter++
                    continue
                }
            } else {
                return jumpedSteps
            }
        } else if (cloudsArray[counter] == 1) {
            continue
        }
    }

    /* for (counter in 0..cloudsNumber) {

     }*/
    return jumpedSteps
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val cloudsNumber = scan.nextLine().trim().toInt()

    val cloudsArray: Array<Int> =
        scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

    val result = jumpingOnClouds(cloudsArray, cloudsNumber)

    println(result)
}
