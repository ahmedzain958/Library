package com.zainco.library.archcomponents.paginganddatabinding.di

import com.zainco.library.archcomponents.paginganddatabinding.ui.bookmark.BookmarkViewModel
import com.zainco.library.archcomponents.paginganddatabinding.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * @author Leopold
 */
val viewModelModule = module {
    viewModel { SearchViewModel(get(), get()) }
    viewModel { BookmarkViewModel(get()) }
}