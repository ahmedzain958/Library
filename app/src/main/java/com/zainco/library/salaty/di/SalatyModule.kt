package com.zainco.library.salaty.di

import android.app.Application
import android.content.SharedPreferences
import com.zainco.library.salaty.Constants.PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SalatyModule {
    @Singleton
    @Provides
    fun provideSharedPreference(application: Application): SharedPreferences {
        return application.getSharedPreferences(
            PREFERENCE_NAME,
            android.content.Context.MODE_PRIVATE
        )
    }
}