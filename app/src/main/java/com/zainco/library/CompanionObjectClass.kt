package com.zainco.library

/*
In Java, you typically have classes that have some static content, but also some non-static content.


Kotlin allows you to do something similar with companion objects, which are objects tied to a class,
meaning a class can access it's companion object's private functions and properties:
 */
class CompanionObjectClass {
    companion object {
        // Things that would be static in Java would go here in Kotlin
        private const val str = "asdf"
    }

    fun example() {
        // I can access private variables in my companion object
        println(str)
    }

}

/*
Note that, even though the members of companion objects look like static members in other languages,
at runtime those are still instance members of real objects, and can, for example, implement interfaces:
*/
