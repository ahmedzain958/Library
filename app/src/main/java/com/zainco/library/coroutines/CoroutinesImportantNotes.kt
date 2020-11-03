package com.zainco.library.coroutines

/**
 * Created by Ahmed Zain on 26/10/2020.
 */
//https://www.raywenderlich.com/1423941-kotlin-coroutines-tutorial-for-android-getting-started
/*
Coroutine Jobs
Open TutorialFragment.kt and a property named parentJob of type Job as follows, under the companion object:

private val parentJob = Job()

As the name suggests, a Job represents a piece of work that needs to be done. Additionally, every Job can be cancelled, finishing its execution. Because of that,
 it has a lifecycle and can also have nested children. Coroutine builders like launch() and async() return jobs as a result.

If you create a child for a Job, by nesting coroutines, it forms a parent-child hierarchy. With the hierarchy,
you can control all the children through this single instance of a Job. If you cancel the parent, all of the children get canceled too.
If a child fails in execution, the rest of the hierarchy fails, as well.

Describing a parent-child hierarchy helps you to control the lifecycle of coroutines from a single instance when using it inside of an Activity or Fragment.

This is why you’re declaring a parentJob. You can use it to cancel and clear up all coroutines which you launched in TutorialFragment.

Next, it’s important to understand how threading works with coroutines.

Using Dispatchers With Kotlin Coroutines
You can execute a coroutine using different CoroutineDispatchers, as mentioned before. Some of the available CoroutineDispatchers in the API are:
 Dispatchers.Main, Dispatchers.IO and Dispatchers.Default.

You can use these dispatchers for the following use cases:

Dispatchers.Default: CPU-intensive work, such as sorting large lists, doing complex calculations and similar. A shared pool of threads on the JVM backs it.
Dispatchers.IO: networking or reading and writing from files. In short – any input and output, as the name states
Dispatchers.Main: recommended dispatcher for performing UI-related events. For example, showing lists in a RecyclerView, updating Views and so on.
You’ll use some of these dispatchers to switch between the main and background threads. One last step before you can launch coroutines – defining a CoroutineScope.

Scoping Kotlin Coroutines
Now, to define the scope when the coroutine runs, you’ll use a custom CoroutineScope to handle the lifecycle of the coroutines.

To do it, declare a property, as shown below, and initialize it under the parentJob:

private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)

The plus() operator helps you create a Set of CoroutineContext elements, which you associate with the coroutines in a particular scope.
The contexts and their elements are a set of rules each Kotlin Coroutine has to adhere to.
private val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob +
      coroutineExceptionHandler)
This set of elements can have information about:

Dispatchers, which dispatch coroutines in a particular thread pool and executor.
CoroutineExceptionHandler, which let you handle thrown exceptions.
Parent Job, which you can use to cancel all Kotlin Coroutines within the scope.
Both the CoroutineDispatcher and a Job implement CoroutineContext. This allows you to sum them – using plus(), to combine their functionality.

Now it’s finally time to launch some Kotlin Coroutines!
 */