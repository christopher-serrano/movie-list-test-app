package com.serranocjm.movielisttestapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("items")
    val items: List<Movie>?,
    @SerializedName("errorMessage")
    val errorMessage: String?
)
