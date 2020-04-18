package com.zainco.library.salaty.di

import android.app.Application
import com.zainco.library.salaty.SalatyApplicationClass
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        SalatyModule::class]
)
interface SalatyAppComponent : AndroidInjector<SalatyApplicationClass> {
    @Component.Builder
    interface Builder {
        //binds a particular object(BaseApplication class ) to the component at the time of its (respect to BaseApplication) construction
        @BindsInstance
        fun application(salatyApplication: Application): Builder

        fun build(): SalatyAppComponent
    }
}