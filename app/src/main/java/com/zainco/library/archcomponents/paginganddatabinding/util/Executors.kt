package com.zainco.library.archcomponents.paginganddatabinding.util

import java.util.concurrent.Executors

/**
 * @author Leopold
 */
private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(f : () -> Unit) = IO_EXECUTOR.execute(f)