package com.zainco.library.letrunapplyalso

fun main() {
    data class Person(var name: String, var tutorial: String)

    var person = Person("Anupam", "Kotlin")

    var l/*I specified Unit*/: Unit = person.let { it.tutorial = "Android" }
    var l2/*I specified Unit*/: Person = person.let {
        it.tutorial = "Android"
        it
    }
    var al: Person = person.also { it.tutorial = "Android" }

    println(l)
    println(l2)
    println(al)
    println(person)
    /*
    The also expression returns the data class object
     whereas the let expression returns nothing (Unit) as we didn’t specify anything explicitly.


     kotlin.Unit
Person(name=Anupam, tutorial=Android)
Person(name=Anupam, tutorial=Android)
Person(name=Anupam, tutorial=Android)
     */
}