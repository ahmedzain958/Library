package com.zainco.library.mvi.data

import com.zainco.library.mvi.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()

}