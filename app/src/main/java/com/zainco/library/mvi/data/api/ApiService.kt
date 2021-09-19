package com.zainco.library.mvi.data.api

import com.zainco.library.mvi.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}