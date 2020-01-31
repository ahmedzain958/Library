package com.zainco.library.archcomponents.paginganddatabinding

import android.app.Application
import com.zainco.library.archcomponents.paginganddatabinding.di.apiModule
import com.zainco.library.archcomponents.paginganddatabinding.di.networkModule
import com.zainco.library.archcomponents.paginganddatabinding.di.roomModule
import com.zainco.library.archcomponents.paginganddatabinding.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * @author Leopold
 */
@Suppress("Unused")
class MVVMApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // declare used Android context
            androidContext(this@MVVMApp)
            // declare modules
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    roomModule,
                    viewModelModule
                )
            )
        }
    }

}