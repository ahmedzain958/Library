package com.zainco.library.inlinefunctions

fun main() {
    inlineFunction {
        println("calling inline function")
    }
    inlineFunction {
        println("calling inline function")
    }
    inlineFunction {
        println("calling inline function")
    }
}

 fun inlineFunction(fn: () -> Unit) {//using inline functions enhances the performance of the high order functions
    // it tells the compiler to copy parameters and functions to the call site not to create a a new instance of the function inside the high order fuction
    fn()
    print("Code inside inline")
}