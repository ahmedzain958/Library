package com.zainco.library.dagger.mitch.di

import com.zainco.library.dagger.mitch.di.auth.AuthModule
import com.zainco.library.dagger.mitch.di.auth.AuthViewModelModule
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
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MitchDaggerMainActivity
}