package com.serranocjm.movielisttestapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("key")
    val key: String?,
    @SerializedName("value")
    val value: String?
)
