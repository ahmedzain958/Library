package com.zainco.library.coroutines.flow.mindorks

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 * Created by Ahmed Zain on 6/27/2020.
 */
@ExperimentalCoroutinesApi
fun main() {
    CoroutineScope(Dispatchers.Main).launch {
        channelFlow {
            (0..10).forEach {
                send(it)
            }
        }.flowOn(Dispatchers.Default)
    }
}