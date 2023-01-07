package com.serranocjm.movielisttestapp.repository

import com.serranocjm.movielisttestapp.data.local.entity.MovieEntity

interface MovieRepository {
    suspend fun fetchMovieList(): List<MovieEntity>?
    suspend fun fetchMovieDetail(id: String): MovieEntity?
}
