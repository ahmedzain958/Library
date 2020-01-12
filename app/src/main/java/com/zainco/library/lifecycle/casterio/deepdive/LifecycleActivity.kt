package com.zainco.library.lifecycle.casterio.deepdive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R

class LifecycleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
//        lifecycle.addObserver(MusicObserver(this))
        val x: TransactionType = "CREDIT".enumOf()
        val x2: TransactionType = "DEBIT".toEnum()
        println("CREDIT $x")
        println("DEBIT $x2")

    }

    enum class TransactionType {
        CREDIT, DEBIT
    }

    inline fun <reified T : Enum<T>> String.enumOf(): T = enumValueOf(this)
    inline fun String.toEnum() = TransactionType.valueOf(this)
}
