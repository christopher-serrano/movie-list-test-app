package com.serranocjm.movielisttestapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class Star(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
