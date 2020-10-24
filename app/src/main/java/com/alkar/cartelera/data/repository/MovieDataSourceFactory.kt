package com.alkar.cartelera.data.repository

import androidx.lifecycle.MutableLiveData
import com.alkar.cartelera.data.api.TheMovieDBInterface
import com.alkar.cartelera.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable
import javax.sql.DataSource

class MovieDataSourceFactory (private val apiService : TheMovieDBInterface, private val compositeDisposable: CompositeDisposable)
    : androidx.paging.DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): androidx.paging.DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }

}