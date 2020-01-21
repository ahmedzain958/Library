package com.zainco.library.dagger.mitch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.zainco.library.dagger.mitch.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val cachedUser = MediatorLiveData<AuthResource<User>>()
    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        if (cachedUser != null) {
            val user: User? = null
            cachedUser.value = AuthResource.loading(user)
            cachedUser.addSource(source) { userAuthResource: AuthResource<User> ->
                cachedUser.value = userAuthResource
            }
        }
    }

    fun logOut() {
        Log.d("logggg", "Logout")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cachedUser
    }
}