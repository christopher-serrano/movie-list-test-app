package com.serranocjm.movielisttestapp.repository

import com.serranocjm.movielisttestapp.data.local.entity.MovieEntity
import com.serranocjm.movielisttestapp.data.remote.network.ApiClient
import org.koin.core.component.KoinComponent

class MovieRepositoryImpl : MovieRepository, KoinComponent {

    val api = ApiClient.invoke()

    override suspend fun fetchMovieList(): List<MovieEntity>? {
        TODO("Not yet implemented")
    }

    override suspend fun fetchMovieDetail(id: String): MovieEntity? {
        TODO("Not yet implemented")
    }
}
