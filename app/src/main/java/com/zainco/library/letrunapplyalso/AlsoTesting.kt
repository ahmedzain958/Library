package com.zainco.library.letrunapplyalso

fun main() {

    val i = 1
    val x = i.also {
        it + 5
    }
    println(x)//1

    val s = "strin"
    val x2 = s.also {
        "${it}g"
    }
    println(x2)//strin


    data class Person(var name: String, var tutorial: String)

    var person = Person("Anupam", "Kotlin")

    var l/*I specified Unit*/: Unit = person.let {
        it.tutorial = "Android"
        println(person)//Person(name=Anupam, tutorial=Android)
    }
    var l2/*I specified Unit*/: Person = person.let {
        it.tutorial = "Android"
        it
    }
    var al: Person = person.also { it.tutorial = "Android" }

    println(l)//kotlin.Unit
    println(l2)//Person(name=Anupam, tutorial=Android)
    println(al)//Person(name=Anupam, tutorial=Android)

    var lett: Int = i.let {it+1 }
    println(lett)//
    /*
    The also expression returns the data class object
     whereas the let expression returns nothing (Unit) as we didn’t specify anything explicitly.
     */
}