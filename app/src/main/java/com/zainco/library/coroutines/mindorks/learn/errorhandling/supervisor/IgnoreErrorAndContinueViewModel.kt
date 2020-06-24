package com.mindorks.example.coroutines.learn.errorhandling.supervisor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.example.coroutines.data.api.ApiHelper
import com.mindorks.example.coroutines.data.local.DatabaseHelper
import com.mindorks.example.coroutines.data.model.ApiUser
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/*
* When Using async

When using async, we need to use the try-catch block to handle the exception like below.

val deferredUser = GlobalScope.async {
    fetchUser()
}
try {
    val user = deferredUser.await()
} catch (exception: Exception) {
    Log.d(TAG, "$exception handled !")
}
Now, let's see some more and the real use-cases of exception handling in Android Development.

Suppose, we have two network calls like below:

getUsers()
getMoreUsers()
And, we are making the network calls in series like below:

launch {
    try {
        val users = getUsers()
        val moreUsers = getMoreUsers()
    } catch (exception: Exception) {
        Log.d(TAG, "$exception handled !")
    }
}
If one of the network calls fail, it will directly go to the catch block.

But suppose, we want to return an empty list for the network call which has failed and continue with the response from the other network call. We can add the try-catch block to the individual network call like below:

launch {
    try {
        val users = try {
            getUsers()
        } catch (e: Exception) {
            emptyList<User>()
        }
        val moreUsers = try {
            getMoreUsers()
        } catch (e: Exception) {
            emptyList<User>()
        }
    } catch (exception: Exception) {
        Log.d(TAG, "$exception handled !")
    }
}
This way, if any error comes, it will continue with the empty list.

Now, what if we want to make the network calls in parallel. We can write the code like below using async.

launch {
    try {
        val usersDeferred = async {  getUsers() }
        val moreUsersDeferred = async { getMoreUsers() }
        val users = usersDeferred.await()
        val moreUsers = moreUsersDeferred.await()
    } catch (exception: Exception) {
        Log.d(TAG, "$exception handled !")
    }
}
Here, we will face one problem, if any network error comes, the application will crash!, it will NOT go to the catch block.

To solve this, we will have to use the coroutineScope like below:

launch {
    try {
        coroutineScope {
            val usersDeferred = async {  getUsers() }
            val moreUsersDeferred = async { getMoreUsers() }
            val users = usersDeferred.await()
            val moreUsers = moreUsersDeferred.await()
        }
    } catch (exception: Exception) {
        Log.d(TAG, "$exception handled !")
    }
}
Now, if any network error comes, it will go to the catch block.

But suppose again, we want to return an empty list for the network call which has failed and continue with the response from the other network call. We will have to use the supervisorScope and add the try-catch block to the individual network call like below:

launch {
    try {
        supervisorScope {
            val usersDeferred = async { getUsers() }
            val moreUsersDeferred = async { getMoreUsers() }
            val users = try {
                usersDeferred.await()
            } catch (e: Exception) {
                emptyList<User>()
            }
            val moreUsers = try {
                moreUsersDeferred.await()
            } catch (e: Exception) {
                emptyList<User>()
            }
        }
    } catch (exception: Exception) {
        Log.d(TAG, "$exception handled !")
    }
}*/
class IgnoreErrorAndContinueViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                // supervisorScope is needed, so that we can ignore error and continue
                // here, more than two child jobs are running in parallel under a supervisor, one child job gets failed, we can continue with other.
                supervisorScope {
                    val usersFromApiDeferred = async { apiHelper.getUsersWithError() }
                    val moreUsersFromApiDeferred = async { apiHelper.getMoreUsers() }

                    val usersFromApi = try {
                        usersFromApiDeferred.await()
                    } catch (e: Exception) {
                        emptyList<ApiUser>()
                    }

                    val moreUsersFromApi = try {
                        moreUsersFromApiDeferred.await()
                    } catch (e: Exception) {
                        emptyList<ApiUser>()
                    }

                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)

                    users.postValue(Resource.success(allUsersFromApi))
                }
            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}