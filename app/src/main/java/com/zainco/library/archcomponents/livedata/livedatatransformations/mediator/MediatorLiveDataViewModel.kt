package com.zainco.library.archcomponents.livedata.livedatatransformations.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediatorLiveDataViewModel : ViewModel() {
    var someLiveData: LiveData<Any> = MutableLiveData("Ahmed")


    /*
    example on mediator live data and LiveData Reactive Streams and Flowables in java
     public void authenticateWithId(int userId){
        final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .subscribeOn(Schedulers.io()));

        authUser.addSource(source, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                authUser.setValue(user);
                authUser.removeSource(source);
            }
        });
    }
     */
    private val id1: MutableLiveData<Int> = MutableLiveData()
    private var id2: MutableLiveData<Int> = MutableLiveData()
    val mediatorLiveData = MediatorLiveData<String>()

    init {
        id1.value = 0
        id2.value = 0
        mediatorLiveData.addSource(id1) {
            mediatorLiveData.value = "A:$it"
        }
        mediatorLiveData.addSource(id2) {
            mediatorLiveData.value = "B:$it"
        }
    }


    fun incId1() {
        id1.value = id1.value?.inc()
    }

    fun incId2() {
        id2.value = id2.value?.inc()
    }
}