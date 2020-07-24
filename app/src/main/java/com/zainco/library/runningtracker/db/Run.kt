package com.zainco.library.runningtracker.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ahmed Zain on 7/24/2020.
 */
@Entity(tableName = "running_table")
data class Run(
    //we won't provide a primary key in the constructor here, instead we will let room to make that
    var img: Bitmap? = null,
    var timeStamp: Long = 0L,//when our run goes(starts)
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var timeInMillis: Long = 0L,//how long our run goes(starts
    var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}