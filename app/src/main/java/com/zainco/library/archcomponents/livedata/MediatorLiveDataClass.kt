package com.zainco.library.archcomponents.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun totalLength(inputs: List<LiveData<String>>): LiveData<Int> {
    val result = MediatorLiveData<Int>()

    val doSum = Observer<String> {
        result.value = inputs.sumBy {
            it.value?.length ?: 0
        }

    }
    inputs.forEach {
        result.addSource(it, doSum)
    }
    return result
}
fun main() {

    val fullname = MutableLiveData<String>()
    val suname = MutableLiveData<String>()
    fullname.postValue("")
    suname.postValue("")

    val total = totalLength(
        listOf(
            fullname,
            suname
        )
    )
    println(total)
}