package com.zainco.library.archcomponents.paging.ui.singlemoviedetails

import androidx.lifecycle.LiveData
import com.zainco.library.archcomponents.paging.data.vo.MovieDetails
import com.zainco.library.archcomponents.paging.data.api.TheMovieDBInterface
import com.zainco.library.archcomponents.paging.data.repository.MovieDetailsNetworkDataSource
import com.zainco.library.archcomponents.paging.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailsNetworkDataSource

    fun fetchSingleMovieDetails(
        movieId: Int,
        compositeDisposable: CompositeDisposable
    ): LiveData<MovieDetails> {
        movieDetailsNetworkDataSource =
            MovieDetailsNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)
        return movieDetailsNetworkDataSource.downloadedMovieDetailsRepsonse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }
}