package com.zainco.library.databinding.mvvmoldviewpagerfragments.di.components

import android.app.Application
import com.zainco.library.databinding.mvvmoldviewpagerfragments.App
import com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules.ActivityBuildersModule
import com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules.AppModule
import com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author Wellington Costa on 31/12/2017.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        //binds a particular object(BaseApplication class ) to the component at the time of its (respect to BaseApplication) construction
        @BindsInstance
        fun application(baseApplication: Application): Builder

        fun build(): AppComponent
    }

}