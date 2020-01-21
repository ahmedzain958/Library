package com.zainco.library.dagger.mitch.ui.auth

import androidx.lifecycle.*
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.zainco.library.dagger.mitch.network.auth.AuthApi
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    var authApi: AuthApi/*, var sessionManager: SessionManager*/
) : ViewModel() {
    val authUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(userId: Int) {
        authUser.value = AuthResource.loading(null as User)
        val liveDataAuthRes: Flowable<AuthResource<User>> = authApi.getUser(userId)
           //instead of calling onError
            .onErrorReturn {
                return@onErrorReturn User(id = -1)
            }
            .map {
                if (it.id == -1) {
                    val error: AuthResource<User> =
                        AuthResource.error("Could not authenticate", User(id = -1))
                    error
                }
                val noError: AuthResource<User> = AuthResource.authenticated(it)
                noError
            }
            .subscribeOn(Schedulers.io())
        val source: LiveData<AuthResource<User>> = LiveDataReactiveStreams.fromPublisher(
            liveDataAuthRes
        )
        authUser.addSource(source, Observer {
            authUser.value = it
            authUser.removeSource(source)
        })
    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }

}
