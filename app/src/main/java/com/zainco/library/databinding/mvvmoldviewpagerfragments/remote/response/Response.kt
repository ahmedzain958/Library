package com.zainco.library.databinding.mvvmoldviewpagerfragments.remote.response

/**
 * @author Wellington Costa on 30/12/2017.
 */
data class Response<out T>(
    val status: Int,
    val data: T?,
    val error: Throwable?
)