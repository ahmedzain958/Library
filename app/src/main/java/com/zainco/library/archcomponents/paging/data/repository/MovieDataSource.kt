package com.zainco.library.archcomponents.paging.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zainco.library.archcomponents.paging.data.api.FIRST_PAGE
import com.zainco.library.archcomponents.paging.data.api.TheMovieDBInterface
import com.zainco.library.archcomponents.paging.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Movie>() {
    /*Int:Key Movie:because we need a list of movie*/

    private var page: Int = FIRST_PAGE
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    // loads the first page
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.getPopularMovie(page = page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.movieList, null, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDataSource", it.message)
                    }
                )
        )
    }

    // loads the next page, called when the user scrolls down
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.getPopularMovie(page = params.key)//params.key: the next page number and incremened automatically I think based o this page+1 in the previous method
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.totalPages >= params.key) { //if we have more pages to load
                            callback.onResult(
                                it.movieList,
                                params.key + 1
                            )//params.key+1 is the next page key
                            networkState.postValue(NetworkState.LOADED)
                        } else {//no more pages
                            networkState.postValue(NetworkState.ENDOFLIST)
                        }
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e("MovieDataSource", it.message)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}