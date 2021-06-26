package com.zainco.library.threading


/*
* () -> Unit is a function that takes nothing as argument, and returns nothing. For example, Runnable.run() is such a function. You can see such a function as a task.

So, (() -> Unit) -> Unit is a function that takes such a function as argument, and returns nothing. For example, Executor.execute(Runnable) is such a function.
* It's thus a function that takes a task as argument (most probably, to execute that task, now, later, once or several times).

Here's an example which defines a function creating what I just called a task, and another function returning an executor

* i.e. a function that executes the task:*/
//function takes nothing as argument and returns nothing
fun createTask(): () -> Unit {
    return {
        ->
        println("Hello world")
    }
}
//function takes function as argument
fun createExecutor() : (() -> Unit) -> Unit {
    return { task: () -> Unit ->
        println("I'm going to execute the task...")
        task()
        println("I'm going to execute the task a second time...")
        task()
    }
}

fun main(args: Array<String>) {
    val task = createTask()
    val executor = createExecutor()
    executor(task)
}