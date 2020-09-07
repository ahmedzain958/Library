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


/*
class CompanionClass {

    companion object CompanionObject {

    }
}

val obj = CompanionClass.CompanionObject
*/

//Also, we can remove the CompanionObject name and in place of the CompanionObject name, we can use the keyword companion i.e. the default name of the companion object will be Companion, like below:

/* CompanionClass {

    companion object {

    }
}
val obj = CompanionClass.Companion
So, following is the example of a companion object in Kotlin:

class ToBeCalled {
    companion object Test {
        fun callMe() = println("You are calling me :)")
    }
}
fun main(args: Array<String>) {
    ToBeCalled.callMe()
}
The output of the above code is “ You are calling me :) ”

Similarly, you can put some variables in the companion object and access it with the help of the class name. The following is an example of the same:

class ToBeCalled {
    companion object Test {
        var someInteger: Int = 10
        fun callMe() = println("You are calling me :)")
    }
}
fun main(args: Array<String>) {
    print(ToBeCalled.someInteger)
}
Note: We can remove the Test name from the above code, it will become like below:

class ToBeCalled {
    companion object {
        var someInteger: Int = 10
        fun callMe() = println("You are calling me :)")
    }
}*/