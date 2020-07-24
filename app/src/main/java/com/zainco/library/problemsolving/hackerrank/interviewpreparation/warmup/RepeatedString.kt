package com.zainco.library.problemsolving.hackerrank.interviewpreparation.warmup

import java.util.*

/**
 * Created by Ahmed Zain on 7/3/2020.
 * https://www.hackerrank.com/challenges/repeated-string/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=warmup
 */
fun repeatedString(smallString: String, stringLength: Long): Long {
    /**
     *reminder = 1 in aba    10
     * reminder = 0 in a    1000000000000
     */
    val remindingStringLength = (stringLength % smallString.length).toInt()
    val stringLengthWithoutRemaining = stringLength - remindingStringLength
    val repeatedTimesWithoutRemaining = stringLengthWithoutRemaining / smallString.length
    var aRepeatedTimes = smallString.repeatedATimes()
    val numberOfARepeatedWithoutRemaining = aRepeatedTimes * repeatedTimesWithoutRemaining
    if (remindingStringLength == 0) {
        return numberOfARepeatedWithoutRemaining
    } else {
        val remindingString = smallString.substring(0, remindingStringLength)
        aRepeatedTimes = numberOfARepeatedWithoutRemaining + remindingString.repeatedATimes()
        return aRepeatedTimes
    }
}

fun String.repeatedATimes(): Long {
    return this.count { ch ->/* a is repeated here aba two times*/
        ch == 'a'
    }.toLong()
}

fun main(args: Array<String>) {
    val scan = Scanner(System.`in`)

    val s = scan.nextLine()

    val n = scan.nextLine().trim().toLong()

    val result = repeatedString(s, n)

    println(result)
}
