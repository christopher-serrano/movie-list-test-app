package com.serranocjm.movielisttestapp.data.remote.network

import com.serranocjm.movielisttestapp.data.remote.model.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface Endpoints {

    @GET("movies.json")
    suspend fun getMovieList(): Response<MovieListResponse?>
}
