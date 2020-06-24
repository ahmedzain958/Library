package com.zainco.library.coroutines.androiddevs

/**
 * Created by Ahmed Zain on 6/24/2020.
 */

//use mindorks tutorial for multi coroutines calls
/*Scopes in Kotlin Coroutines
Scopes in Kotlin Coroutines are very useful because we need to cancel the background task as soon as the activity is destroyed.
 Here, we will learn how to use scopes to handle these types of situations.

Assuming that our activity is the scope, the background task should get canceled as soon as the activity is destroyed.

In the activity, we need to implement CoroutineScope.

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

}
In the onCreate and onDestroy function.

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    job = Job() // create the Job
}

override fun onDestroy() {
    job.cancel() // cancel the Job
    super.onDestroy()
}
Now, just use the launch like below:

launch {
    val userOne = async(Dispatchers.IO) { fetchFirstUser() }
    val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
    showUsers(userOne.await(), userTwo.await())
}
As soon as the activity is destroyed, the task will get canceled if it is running because we have defined the scope.*/
/*
* When we need the global scope which is our application scope, not the activity scope, we can use the GlobalScope as below:

GlobalScope.launch(Dispatchers.Main) {
    val userOne = async(Dispatchers.IO) { fetchFirstUser() }
    val userTwo = async(Dispatchers.IO) { fetchSecondUser() }
}
Here, even if the activity gets destroyed, the fetchUser functions will continue running as we have used the GlobalScope.*/