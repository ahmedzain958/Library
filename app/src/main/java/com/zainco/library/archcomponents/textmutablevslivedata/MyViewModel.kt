package com.zainco.library.archcomponents.textmutablevslivedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    // LiveData object as following
    var someLiveData: LiveData<Any> = MutableLiveData("Ahmed")

}
data class User(var name: String, var age: Int)

class DemoLiveData: LiveData<User>()

var demoLiveData: LiveData<User>? = DemoLiveData()

fun main() {
//    demoLiveData?.value = User("Name", 23) // ERROR
    demoLiveData?.value?.name = "Name" // NO ERROR
    demoLiveData?.value?.age = 23  // NO ERROR
}