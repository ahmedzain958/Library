package com.zainco.library.databinding.mvvmoldviewpagerfragments.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zainco.library.databinding.mvvmoldviewpagerfragments.di.keys.ViewModelKey
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors.ListColorsViewModel
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.common.ViewModelFactory
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users.ListUsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Wellington Costa on 31/12/2017.
 */
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListUsersViewModel::class)
    fun bindListUsersViewModel(listUsersViewModel: ListUsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListColorsViewModel::class)
    fun bindListColorsViewModel(listColorsViewModel: ListColorsViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}