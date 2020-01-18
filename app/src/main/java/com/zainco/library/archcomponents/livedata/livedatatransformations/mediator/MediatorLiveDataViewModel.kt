package com.zainco.library.archcomponents.livedata.livedatatransformations.mediator

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediatorLiveDataViewModel : ViewModel() {

    private var id1: MutableLiveData<Int> = MutableLiveData()
    private var id2: MutableLiveData<Int> = MutableLiveData()
    private var mId1: MediatorLiveData<Int> = MediatorLiveData()

    init {
        mId1.addSource(id1) {
            mId1.value = id1.value
            mId1.removeSource(id1)
        }
        mId1.addSource(id2) {
            mId1.value = id2.value
            mId1.removeSource(id2)
        }
    }

    fun getId1() = id1
    fun getId2() = id2
    fun setId1(id: Int) {
        id1.value = id
    }

    fun setId2(id: Int) {
        id1.value = id
    }
}