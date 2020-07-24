package com.zainco.library.runningtracker.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Ahmed Zain on 7/24/2020.
 */
@Dao
interface RunDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//when we want to insert a run that is already existing in the db, will be replaced by the new run
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    //this doesn't work in coroutine so we can't prepend this method by suspend fun
    @Query("SELECT * FROM running_table ORDER BY timeStamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY timeInMillis DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT SUM(timeInMillis) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT AVG(avgSpeedInKMH) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>
}