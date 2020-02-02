package com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules

import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.FragmentsViewPagerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Wellington Costa on 31/12/2017.
 */
@Module
interface ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    fun contributeMainActivity(): FragmentsViewPagerActivity

}