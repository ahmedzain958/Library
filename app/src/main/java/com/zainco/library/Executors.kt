/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zainco.library

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

private val IO_EXECUTOR: ExecutorService = Executors.newSingleThreadExecutor()

/**
 * Utility method to run blocks on a dedicated background thread, used for io/database work.
 */
//val myLamda :() -> Unit = {-> println("")}
fun ioThread(f: () -> Unit) {
    IO_EXECUTOR.execute(f)
}

// to use this method from outside
/*
* private fun fillInDb(context: Context) {
            // inserts in Room are executed on the current thread, so we insert in the background
            ioThread {//because ioThread is a fun with only one parameter as a fun so it is called {} trailing lamda
                get(context).cheeseDao().insert(
                        CHEESE_DATA.map {
                                Cheese(id = 0, name = it) })
            }
        }
*/