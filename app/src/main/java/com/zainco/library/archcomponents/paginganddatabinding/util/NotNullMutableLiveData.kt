package com.zainco.library.archcomponents.paginganddatabinding.util

import androidx.lifecycle.MutableLiveData

/**
 * @author Leopold
 */
class NotNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue()  = super.getValue()!!
}