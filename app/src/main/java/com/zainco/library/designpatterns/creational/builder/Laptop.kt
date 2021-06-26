package com.zainco.library.designpatterns.creational.builder

class Laptop(builder: Builder) {
    private val processor: String = builder.processor
    private val ram: String = builder.ram
    private val battery: String = builder.battery
    private val screenSize: String = builder.screenSize

    // Builder class
    class Builder(processor: String) {
        var processor: String = processor // this is necessary

        // optional features
        var ram: String = "2GB"
        var battery: String = "5000MAH"
        var screenSize: String = "15inch"

        fun setRam(ram: String): Builder {
            this.ram = ram
            return this
        }

        fun setBattery(battery: String): Builder {
            this.battery = battery
            return this
        }

        fun setScreenSize(screenSize: String): Builder {
            this.screenSize = screenSize
            return this
        }

        fun create(): Laptop {
            return Laptop(this)
        }
    }
}

fun main() {
    val laptop: Laptop = Laptop.Builder("i7") // processor is compulsory
        .setRam("8GB")            // this is optional
        .setBattery("6000MAH")    // this is optional
        .create()
    println(laptop.toString())
}