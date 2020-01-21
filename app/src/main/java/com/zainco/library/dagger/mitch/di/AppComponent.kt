package com.zainco.library.dagger.mitch.di

import android.app.Application
import com.zainco.library.dagger.mitch.BaseApplication
import com.zainco.library.dagger.mitch.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * AndroidSupportInjectionModule: You always need to add it to the modules
 * ActivityBuildersModule: all the injected activities
 */
@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class]
)

interface AppComponent : AndroidInjector<BaseApplication> {
    //will be kept alive as long as application is alive
    fun sessionManager():SessionManager
    @Component.Builder
    interface Builder {
        //binds a particular object(BaseApplication class ) to the component at the time of its (respect to BaseApplication) construction
        @BindsInstance
        fun application(baseApplication: Application): Builder

        fun build(): AppComponent
    }
}