package com.zainco.library.dagger.mitch.di.auth

import androidx.lifecycle.ViewModel
import com.zainco.library.dagger.mitch.di.ViewModelKey
import com.zainco.library.dagger.mitch.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
* i have one viewmodel in my Auth Package (
*   1- authentication
* )
*
* so as we talked about dagger/viewModel problem
* we call it in this way
* don't care if u did't understand it just put ur  @ViewModelKey(ViewModelClassName::class)
* it will be injected
* */
@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel

}