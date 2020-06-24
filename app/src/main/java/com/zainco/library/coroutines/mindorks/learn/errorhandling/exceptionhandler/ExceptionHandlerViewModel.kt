package com.mindorks.example.coroutines.learn.errorhandling.exceptionhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.example.coroutines.data.api.ApiHelper
import com.mindorks.example.coroutines.data.local.DatabaseHelper
import com.mindorks.example.coroutines.data.model.ApiUser
import com.mindorks.example.coroutines.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/*
* When Using launch

One way is to use try-catch block:

GlobalScope.launch(Dispatchers.Main) {
    try {
        fetchUserAndSaveInDatabase() // do on IO thread and back to UI Thread
    } catch (exception: Exception) {
        Log.d(TAG, "$exception handled !")
    }
}
Another way is to use a handler:

For this we need to create an exception handler like below:

val handler = CoroutineExceptionHandler { _, exception ->
    Log.d(TAG, "$exception handled !")
}
Then, we can attach the handler like below:

GlobalScope.launch(Dispatchers.Main + handler) {
    fetchUserAndSaveInDatabase() // do on IO thread and back to UI Thread
}
If there is an exception in fetchUserAndSaveInDatabase, it will be handled by the handler which we have attached.

When using in the activity scope, we can attach the exception in our coroutineContext as below:

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + handler

    private lateinit var job: Job

}
* And use like below:

launch {
    fetchUserAndSaveInDatabase()
}*/
class ExceptionHandlerViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        users.postValue(Resource.error("Something Went Wrong", null))
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(exceptionHandler) {
            users.postValue(Resource.loading(null))
            val usersFromApi = apiHelper.getUsers()
            users.postValue(Resource.success(usersFromApi))
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}