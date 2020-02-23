package com.zainco.library.archcomponents.paging.singlemoviedetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.oxcoding.moviemvvm.data.vo.MovieDetails
import com.zainco.library.R
import com.zainco.library.archcomponents.paging.data.api.POSTER_BASE_URL
import com.zainco.library.archcomponents.paging.data.api.TheMovieDBClient
import com.zainco.library.archcomponents.paging.data.api.TheMovieDBInterface
import kotlinx.android.synthetic.main.activity_single_movie.*
import java.text.NumberFormat
import java.util.*

class SingleMovieActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleMovieViewModel
    private lateinit var movieRepository: MovieDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        val movieId: Int = 1
        val apiService: TheMovieDBInterface = TheMovieDBClient.getClient()
        movieRepository = MovieDetailsRepository(apiService)
        viewModel = getViewModel(movieId)
        viewModel.movieDetails.observe(this, Observer {
buildUI(it)
        })

    }

    private fun buildUI(it: MovieDetails) {
        movie_title.text = it.title
        movie_tagline.text = it.tagline
        movie_release_date.text = it.releaseDate
        movie_rating.text = it.rating.toString()
        movie_runtime.text = it.runtime.toString() + " minutes"
        movie_overview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        movie_budget.text = formatCurrency.format(it.budget)
        movie_revenue.text = formatCurrency.format(it.revenue)

        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(iv_movie_poster);
    }

    private fun getViewModel(movieId: Int): SingleMovieViewModel {
        val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SingleMovieViewModel(movieRepository, movieId) as T
            }
        }
        return ViewModelProvider(this, viewModelFactory).get(SingleMovieViewModel::class.java)
        //the upper part instead of the next
        /* return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
             override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                 return SingleMovieViewModel(movieRepository, movieId) as T
             }
         })[SingleMovieViewModel::class.java]*/

        // if ytou don't use factory use MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    }
}
