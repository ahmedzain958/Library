package com.zainco.library.archcomponents.paging.data.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.zainco.library.archcomponents.paging.data.api.TheMovieDBInterface
import com.zainco.library.archcomponents.paging.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable
//same params of datasource
class MovieDataSourceFactory(private val apiService: TheMovieDBInterface,
                             private val compositeDisposable: CompositeDisposable
): DataSource.Factory<Int, Movie>() {
    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)
        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }

}