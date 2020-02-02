package com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.repository

import com.zainco.library.databinding.mvvmoldviewpagerfragments.data.remote.Api
import com.zainco.library.databinding.mvvmoldviewpagerfragments.data.remote.response.ApiListResponse
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.Color
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @author Wellington Costa on 30/12/2017.
 */
open class ColorRepository @Inject constructor(private val api: Api) {

    fun getColors(): Observable<ApiListResponse<Color>> {
        return api.fetchColors()
    }

}