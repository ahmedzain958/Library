package com.zainco.library.dagger.mitch.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.zainco.library.dagger.mitch.SessionManager
import com.zainco.library.dagger.mitch.network.auth.AuthApi
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    var authApi: AuthApi, var sessionManager: SessionManager
) : ViewModel() {

    fun authenticateWithId(userId: Int) {
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
        val liveDataAuthRes: Flowable<AuthResource<User>> = authApi.getUser(userId)
            //instead of calling onError
            .onErrorReturn {
                return@onErrorReturn User(id = -1)
            }
            .map {
                if (it.id == -1) {
                    AuthResource.error("Could not authenticate", User(id = -1))
                }
                AuthResource.authenticated(it)
            }
            .subscribeOn(Schedulers.io())
        val source: LiveData<AuthResource<User>> =
            LiveDataReactiveStreams.fromPublisher(//should take reactive rx to be converted to LiveData
                liveDataAuthRes
            )
        return source
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}
