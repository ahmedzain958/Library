package com.zainco.library


/*
A Kotlin object is like a class that can't be instantiated so it must be called by name
 known as a singleton.

 Object declaration's initialization is thread-safe.

NOTE: object declarations can't be local (i.e. be nested directly inside a function),
 but they can be nested into other object declarations or non-inner classes



 An object is a singleton. You do not need to create an instance to use it.
A class needs to be instantiated to be used



 */
object ExampleObject {
    fun example() {
    }
}

/*
In order to use the ExampleClass, you need to create an instance of it: ExampleClass().example(),
 but with an object, Kotlin creates a single instance of it for you, and you don't ever call it's constructor,
 */
class ExampleClass {
    fun example() {
    }
}