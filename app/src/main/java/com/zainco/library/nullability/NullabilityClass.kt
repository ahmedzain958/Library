package com.zainco.library.nullability

/*
fun main() {
    val b: B? = null
    val a: Int = b?.x ?: 0
    val a2: Int = b?.array?.size ?: 0
    println(a)//==>0
    println(a2)//==>0
}

class B {
    val x: Int? = 6
    val array: List<Int>? = null
}
*/



fun main() {
    val b: B? = B()
    val a: Int = b?.x ?: 0
    val a2: Int = b?.array?.size ?: 0
    println(a)//==>0
    println(a2)//==>0
}

class B {
    val x: Int? = 6
    val array: List<String>? = listOf("1", "5", "8")
}
