package com.zainco.library.coroutines

import java.util.concurrent.CompletableFuture

fun main() {
    val fred = Barista("Fred")
    val sam = Barista("Sam")

    sam.acceptOrder(CoffeeType.AMERICANO)
    fred.acceptOrder(CoffeeType.LATTE)
}

/**
 * A Barista can accept Coffee Type orders
 *
 * @param name: The name of the Barista
 */
class Barista(val name: String) {
    private val coffeeMaker = CoffeeMaker()

    fun acceptOrder(type: CoffeeType) {
        // brew coffee takes parameter/doesn't take any parameter but the listener(callback) returns the brewed coffee
        coffeeMaker.brewCoffee(type) { coffee ->
            println("$name finished brewing ${coffee.type}")
        }
    }
}

/**
 * The Carrier Object for the finished order
 */
data class Coffee(val type: CoffeeType)
/**
 * The device that will emulate time passing
 */
class CoffeeMaker {
    fun brewCoffee(type: CoffeeType, onBrewed: (Coffee) -> Unit) {
        delay(type.brewTime)
        val madeCofee = Coffee(type)
        onBrewed(madeCofee)//== onBrewed.invoke(madeCofee)
    }
}

/**
 * The Coffee Type
 *
 * @param brewTime: emulates time
 */
enum class CoffeeType(val brewTime: Long) {
    AMERICANO(300L),
    CAPPUCCINO(950L),
    DRIP(100L),
    ESPRESSO(800L),
    LATTE(875L)
}

/**
 * Emulate a delay by blocking the main thread.
 *
 * Warning: Never use this (lol)
 */
fun delay(time: Long) {
    Thread.sleep(time)
}

