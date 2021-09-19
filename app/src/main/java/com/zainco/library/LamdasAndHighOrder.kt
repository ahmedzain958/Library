package com.zainco.library

import java.util.concurrent.CompletableFuture

//lamda is just a fn with no name
fun main1() {
    fun foo(bar: Int = 0, baz: Int = 1, qux: () -> Unit) { /*fun inside fun*/
    }

    foo(1) { println("hello") }     // Uses the default value baz = 1
    foo(qux = { println("hello") }) // Uses both default values bar = 0 and baz = 1
    foo { println("hello") }        // Uses both default values bar = 0 and baz = 1
}

//smartherd
fun main(args: Array<String>) {

    val program = Program()

    program.addTwoNumbers(2, 7)     // Simple way... for better understanding

    program.addTwoNumbers(2,
        7, /*since kotlin has no new keyword, we use object keyword followed by a colon : */
        object : MyInterface {   // Using Interface / Object oriented programming way
            override fun execute(sum: Int) {
                println(sum)    // Body
            }
        })

    val test: String = "Hello"
//by similarity to the upper line we provided the test variable by a type, so can either do
    /*method(1): just param type - val myLambda = { s: Int -> println(s) } */
    /*method(2):  variable type  - val myLambda: (Int) -> Unit = { s: Int -> println(s) } */
    val myLambda: (Int) -> Unit =
        { s: Int ->
            println(s)
        }   // Lambda Expression [ Function ] :  lamda expression/lamda function  is nothing but just a function with no name {s->println(s)}
    myLambda(3)

    //the next 4 calls are equal
    program.addTwoNumbers(2, 7, myLambda)
    program.addTwoNumbers(2, 7, myFn())
    program.addTwoNumbers(2, 7, {//method can be called by two curly { } which called a lamda
            s: Int ->
        println(s)
    })
    program.addTwoNumbers(2, 7) { s: Int ->//when the paramter function is the last porameter it can be after the parenthesis
        //then it is called trailing lamda
        println(s)
    }
}

private fun myFn() = {// equals the upper myLambda variable
        s: Int ->
    println(s)
}

class Program {

    fun addTwoNumbers(
        a: Int,
        b: Int,
        action: (Int) -> Unit,//even when you define a function as a parameter, you will define a normal variable name then colon: then in the place of the type
        //(which is after the colon) you need to put the parenthesis first and put all the parameters types required and if no params leave it empty
        //then use the return type for your function after the arrow operator, but even if you don't need to have a return type you must put a return type (Unit)
    ) {  // High Level Function with Lambda as Parameter
        val sum = a + b
        action(sum)     // println(sum)
//        println(sum)  // Body
    }

    fun addTwoNumbers(
        a: Int,
        b: Int,
        action: MyInterface,
    ) {    // Using Interface / Object Oriented Way
        val sum = a + b
        action.execute(sum)
    }

    fun addTwoNumbers(
        a: Int,
        b: Int,
    ) {                         // Simple way.. Just for Better Understanding

        val sum = a + b
        println(sum)
    }

    fun ssss(
        action: () -> Unit
    ) {
       action.invoke()
    }
}

interface MyInterface {
    fun execute(sum: Int)
}

private fun myFn1():Unit  {// equals the upper myLambda variable
    println("s")
    return
}

