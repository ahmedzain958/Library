package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zainco.library.databinding.mvvmoldviewpagerfragments.remote.response.Response

/**
 * @author Wellington Costa on 31/12/2017.
 */
abstract class BaseViewModel<T> : ViewModel() {

    val response: MutableLiveData<Response<T>> = MutableLiveData()

    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()

    abstract fun loadData()

}