package com.zainco.library.databinding.mvvmoldviewpagerfragments

import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/**
 * @author Wellington Costa on 31/12/2017.
 */
open class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return com.zainco.library.dagger.mitch.di.DaggerAppComponent.builder().application(this)
            .build()
    }

}