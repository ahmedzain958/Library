package com.zainco.library

/**
 * Created by Ahmed Zain on 6/30/2020.
 */
class User(
    name: String,
    phone_number: String,
    color: Int,
    timestamp: Long,
    blockType: Int,
    var reason: String
) {
    var name: String = name
        private set
    var phoneNumber: String = phone_number
        private set
    var color = color
        private set
    var timestamp: Long = timestamp
        private set
    var blockType: Int = blockType
        private set
}

fun main() {
    val user = User("name", "010", 0, 588185L, 45, "")
}