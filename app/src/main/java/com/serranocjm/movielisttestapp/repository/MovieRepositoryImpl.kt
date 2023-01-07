package com.serranocjm.movielisttestapp.repository

import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.data.remote.network.ApiClient
import com.serranocjm.movielisttestapp.data.utils.Result
import org.koin.core.component.KoinComponent

class MovieRepositoryImpl : MovieRepository, KoinComponent {

    val api = ApiClient.invoke()
    override suspend fun fetchMovieList(fromRemote: Boolean): Result<List<Movie>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMovieDetail(id: String): Result<Movie> {
        TODO("Not yet implemented")
    }
}
