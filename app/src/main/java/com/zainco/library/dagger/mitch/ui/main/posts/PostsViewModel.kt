package com.zainco.library.dagger.mitch.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.models.Post
import com.google.firebase.udacity.daggerPosts.dagger.models.User
import com.zainco.library.dagger.mitch.SessionManager
import com.zainco.library.dagger.mitch.network.main.MainApi
import com.zainco.library.dagger.mitch.ui.auth.AuthResource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/*
* all of dagger classes that we built it we can use it now easily
*   Two ways to use the injected classes
*   1 - @Inject lateinint var api:AuthApi
*   2 - inject it in the constructor    EX @Inject constructor(var authApi: AuthApi)
* */
class PostsViewModel @Inject constructor(
    var sessionManager: SessionManager, var mainApi: MainApi,
    private var disposable: CompositeDisposable
) : ViewModel() {
    private var postsTest: MutableLiveData<List<Post>> = MutableLiveData()
    private var userId: MutableLiveData<Int> = MutableLiveData()
    private val TAG: String = "PostsViewModelTag"


    init {
        val observer: Observer<AuthResource<User>> = Observer {
            userId.value = it.data?.id
            Log.d(TAG, "userId ${userId}")
        }
        sessionManager.getAuthUser().apply {
            observeForever(observer)
            Log.d(TAG, "observer  ${observer}")
            sessionManager.getAuthUser().removeObserver(observer)
            Log.d(TAG, "observer removed ${observer}")
        }
    }


    /*
    * api request to get user posts
    * */
    fun observeMyPosts(): LiveData<List<Post>> {
        /*   val x = Transformations.map()
           sessionManager.getAuthUser().value.*/
        userId.value?.let {
            Log.d(TAG, "id after observe  ${it}")
            disposable.add(
                mainApi.getPosts(
                    it
                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        postsTest.value = it
                    }
            )
        }

        return postsTest
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
        disposable.dispose()
    }
}