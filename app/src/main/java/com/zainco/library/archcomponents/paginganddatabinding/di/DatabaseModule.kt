package com.zainco.library.archcomponents.paginganddatabinding.di

import com.zainco.library.archcomponents.paginganddatabinding.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

/**
 * @author Leopold
 */
val roomModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
    single { get<AppDatabase>().getBookmarkDao() }
}