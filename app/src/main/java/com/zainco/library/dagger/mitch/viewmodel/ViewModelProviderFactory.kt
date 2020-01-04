package com.zainco.library.dagger.mitch.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/*
* NOT THAT IS A FIXED CLASS u can use it at any project to solve the problem of
* dagger2 and ViewModel because both are not working with each other
* just copy past it and do not care about the implementation
* it is valid for all p
* rojects
*
* */
class ViewModelProviderFactory @Inject constructor(private val creators: Map<Class<out ViewModel?>?, Provider<ViewModel>?>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator: Provider<out ViewModel>? = creators[modelClass]
        if (creator == null) { // if the viewmodel has not been created
// loop through the allowable keys (aka allowed classes with the @ViewModelKey)
            for ((key, value) in creators) { // if it's allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(key!!)) {
                    creator = value
                    break
                }
            }
        }
        // if this is not one of the allowed keys, throw exception
        requireNotNull(creator) { "unknown model class $modelClass" }
        // return the Provider
        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    companion object {
        private const val TAG = "ViewModelProviderFactor"
    }

}