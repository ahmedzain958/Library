package com.zainco.library.archcomponents.paging.listtopagelist

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_list_to_pagelist.*
import java.util.*
import java.util.concurrent.Executor


class ListToPagelistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_to_pagelist)
        val myList: MutableList<Any> =
            ArrayList()
        myList.add("Ahmed")
        myList.add("Mohamed")
        myList.add("Zain")
        myList.add("Ahmed")
        myList.add(15F)
        val builder = StringBuilder()
        builder.append(Thread.currentThread().name + "n")

        textView14.text =
            builder.append(ListToPagelist().getPagedList(myList).toString()).toString()

    }

    class UiThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            handler.post(command)
        }
    }
}