package com.serranocjm.movielisttestapp.repository

import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.data.utils.Result

interface MovieRepository {
    suspend fun fetchMovieList(fromRemote: Boolean): Result<List<Movie>>
    suspend fun fetchMovieDetail(id: String): Result<Movie>
}
