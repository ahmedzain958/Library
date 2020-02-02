package com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules

import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors.ListColorsFragment
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users.ListUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Wellington Costa on 31/12/2017.
 */
@Module
interface FragmentBuildersModule {

    @ContributesAndroidInjector
    fun contributeListUsersFragment(): ListUsersFragment

    @ContributesAndroidInjector
    fun contributeListColorsFragment(): ListColorsFragment

}