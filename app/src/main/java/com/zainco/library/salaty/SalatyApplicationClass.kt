package com.zainco.library.salaty

import com.zainco.library.salaty.di.DaggerSalatyAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SalatyApplicationClass : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerSalatyAppComponent.builder().application(this).build()
    }

}