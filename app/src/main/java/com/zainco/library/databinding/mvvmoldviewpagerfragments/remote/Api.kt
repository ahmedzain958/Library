package com.zainco.library.databinding.mvvmoldviewpagerfragments.data.remote

import com.zainco.library.databinding.mvvmoldviewpagerfragments.data.remote.response.ApiListResponse
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.Color
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Wellington Costa on 30/12/2017.
 */
interface Api {

    @GET("users")
    fun fetchUsers(): Observable<ApiListResponse<User>>

    @GET("colors")
    fun fetchColors(): Observable<ApiListResponse<Color>>

}