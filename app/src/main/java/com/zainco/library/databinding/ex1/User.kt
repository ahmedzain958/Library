package com.zainco.library.databinding.ex1

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

data class User(
    private val _name: String,
    private val _email: String
) : BaseObservable() {
    var name: String = ""
        @Bindable get() = _name
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var email: String = ""
        @Bindable get() = _email
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }
}