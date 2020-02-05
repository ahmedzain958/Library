package com.zainco.library.dagger.mitch.di

import com.zainco.library.dagger.mitch.di.auth.AuthModule
import com.zainco.library.dagger.mitch.di.auth.AuthScope
import com.zainco.library.dagger.mitch.di.auth.AuthViewModelModule
import com.zainco.library.dagger.mitch.di.main.MainFragmentBuildersModule
import com.zainco.library.dagger.mitch.di.main.MainModule
import com.zainco.library.dagger.mitch.di.main.MainScope
import com.zainco.library.dagger.mitch.di.main.MainViewModelsModule
import com.zainco.library.dagger.mitch.ui.auth.AuthActivity
import com.zainco.library.dagger.mitch.ui.main.MitchDaggerMainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    /*
      *  @ContributesAndroidInjector tells dagger hey!! i wanna this activity to be aware with dagger classes
      *  AuthViewModelModule::class, AuthModule::class just handling some logic in AuthActivity
      * */
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    //MainFragmentBuildersModule will exist within the scope MitchDaggerMainActivity subcomponent
    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class]
    )
    abstract fun contributeMainActivity(): MitchDaggerMainActivity
}