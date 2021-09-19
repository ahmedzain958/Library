package com.zainco.library.mvi.data.api

import com.zainco.library.mvi.data.model.User


interface ApiHelper {

    suspend fun getUsers(): List<User>

}