package com.zainco.library.runningtracker.di

import android.content.Context
import androidx.room.Room
import com.zainco.library.runningtracker.db.RunningDatabase
import com.zainco.library.runningtracker.other.Constants.RUNNING_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Ahmed Zain on 7/25/2020.
 */
@Module
@InstallIn(ApplicationComponent::class)//dependencies live as long as app does
object AppModule {
    /*if we left the next method without @Singletone annotation, this means whenever we want to inject the db into
    * some classes of us, it will create a new instance of db each time*/
    @Provides
    @Singleton
    fun provideRunningDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        RunningDatabase::class.java,
        RUNNING_DATABASE_NAME
    )

    @Provides
    @Singleton
    fun provideRunDao(db: RunningDatabase/*dagger already knows how to create running database from the upper provide method*/) =
        db.getRunDao()
}