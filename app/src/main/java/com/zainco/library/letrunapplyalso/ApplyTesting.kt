package com.zainco.library.letrunapplyalso

fun main() {
    data class Person(var n: String, var t: String)

    var person = Person("Anupam", "Kotlin")

    val apply: Person = person.apply {
        t = "Swift" }
    println(person)
    println(apply)

    val also: Person = person.also {
        it.t = "Zain" }
    println(person)
    println(also)
    //We should use also only when we don’t want to shadow this.




}