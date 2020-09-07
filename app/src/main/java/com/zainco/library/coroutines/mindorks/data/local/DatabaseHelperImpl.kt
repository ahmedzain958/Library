package com.mindorks.example.coroutines.data.local

import com.mindorks.example.coroutines.data.local.entity.User
import com.zainco.library.coroutines.mindorks.data.local.AppDatabase

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {

    override suspend fun getUsers(): List<User> = appDatabase.userDao().getAll()

    override suspend fun insertAll(users: List<User>) = appDatabase.userDao().insertAll(users)

}