package com.zainco.library.archcomponents.paginganddatabinding.di

import com.zainco.library.archcomponents.paginganddatabinding.data.remote.api.SearchAPI
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * @author Leopold
 */
val apiModule = module {
    single { get<Retrofit>().create(SearchAPI::class.java) }
}