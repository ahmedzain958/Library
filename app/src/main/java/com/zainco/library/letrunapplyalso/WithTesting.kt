package com.zainco.library.letrunapplyalso

fun main() {

    data class Person(var name: String, var tutorial : String)
    var person = Person("Anupam", "Kotlin")

  with(person)
    {
        name = "No Name"
        tutorial = "Kotlin tutorials"
    }
}