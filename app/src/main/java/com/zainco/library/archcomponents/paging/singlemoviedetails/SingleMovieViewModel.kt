package com.zainco.library.archcomponents.paging.singlemoviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.oxcoding.moviemvvm.data.vo.MovieDetails
import com.zainco.library.archcomponents.paging.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class SingleMovieViewModel(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val movieId: Int
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()


    // called when we need it not when the view model is created
    val movieDetails: LiveData<MovieDetails> by lazy {
        movieDetailsRepository.fetchSingleMovieDetails(movieId, compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieDetailsRepository.getMovieDetailsNetworkState()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}