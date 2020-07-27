package com.zainco.library.runningtracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by Ahmed Zain on 7/24/2020.
 */
@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Converters::class)//tells room that it will have type converters so use them
abstract class RunningDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDAO
}