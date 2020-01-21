package com.zainco.library.dagger.mitch.network.auth

import com.google.firebase.udacity.daggerPosts.dagger.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}