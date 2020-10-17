package com.zainco.library

/**
 * Created by Ahmed Zain on 29/09/2020.
 */
/*
* Nothing
This type does not exist in Java. It is used when a function will never terminate normally and therefore a return value has no sense.
It is very useful when analyzing this type of code, to know that the function will never terminate.
An example of this kind of functions are the fail function in test systems, or the main loop in a game engine.
fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

val address = employee.address ?: fail("${employee.name} has no address defined")
println(address)
 */
/*
Unit
In Java if we want that a function does return nothing we use void, Unit is the equivalent in Kotlin.
The main characteristics of Unit against Java’s void are:
Unit is a type and therefore can be used as a type argument.
Only one value of this type exists.
It is returned implicitly. No need of a return statement.
interface DataProcessor<T> {
    fun processData(): T
}

class NoResultDataProcessor : DataProcessor<Unit> { // Use "no value" as a type argument
    override fun processData() {
        ...
        // No need of a explicit return
    }
}
 */