package com.zainco.library.oop.abstract

/**
 * Created by Ahmed Zain on 7/24/2020.
 */
class MyClass : Abstracted() {
    override fun go() {
        //must implement abstract class abstracted method
    }
}

abstract class MyClass2 : Abstracted() {
    //as long this class is abstract and extends abstract class; no need to implement the extended abstract class
    //ex : no compile time error here
    /* abstract class RunningDatabase : RoomDatabase() {
     }*/
}

abstract class Abstracted {
    abstract fun go()
    fun go1() {//if it isnt abstract it must have body}
    }
}
