package com.zainco.library.dagger.mitch.di.main

import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.ui.main.profile.ProfileViewModel
import com.zainco.library.dagger.mitch.di.ViewModelKey
import com.zainco.library.dagger.mitch.ui.main.posts.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel): ViewModel
}