package com.zainco.library.dagger.mitch.di.main

import com.zainco.library.dagger.mitch.ui.main.posts.PostsFragment
import com.zainco.library.dagger.mitch.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

//fragments that exist in the main component
@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}