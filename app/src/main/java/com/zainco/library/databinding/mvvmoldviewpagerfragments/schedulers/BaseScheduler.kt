package com.zainco.library.databinding.mvvmoldviewpagerfragments.util.schedulers

import androidx.annotation.NonNull
import io.reactivex.Scheduler


/**
 * @author Wellington Costa on 31/12/2017.
 */
interface BaseScheduler {

    @NonNull
    fun io(): Scheduler

    @NonNull
    fun ui(): Scheduler

}