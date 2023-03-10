package com.serranocjm.movielisttestapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.serranocjm.movielisttestapp.utils.parseDuration

data class Movie(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("fullTitle")
    val fullTitle: String?,
    @SerializedName("year")
    val year: String?,
    @SerializedName("releaseState")
    val releaseState: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("runtimeMins")
    val runtimeMins: String?,
    @SerializedName("runtimeStr")
    val runtimeStr: String?,
    @SerializedName("plot")
    val plot: String?,
    @SerializedName("contentRating")
    val contentRating: String?,
    @SerializedName("imDbRating")
    val imDbRating: String?,
    @SerializedName("imDbRatingCount")
    val imDbRatingCount: String?,
    @SerializedName("metacriticRating")
    val metacriticRating: String?,
    @SerializedName("genres")
    val genres: String?,
    @SerializedName("genreList")
    val genreList: List<Genre>?,
    @SerializedName("directors")
    val directors: String?,
    @SerializedName("directorList")
    val directorList: List<Director>?,
    @SerializedName("stars")
    val stars: String?,
    @SerializedName("starList")
    val starList: List<Star>?
) {
    fun getBasicInfo(): String {
        val rating = if (this.contentRating.isNullOrBlank()) "N/A" else this.contentRating
        return "${this.year} - ${getFormattedRunningTime()} - $rating"
    }

    fun getFormattedDirectorList(): String {
        return "Direction: ${this.directors ?: "No director list available."}"
    }

    fun getFormattedCastList(): String {
        return "Cast: ${this.stars ?: "No cast list available."}"
    }

    fun getFormatterGenreList(): String {
        return "Genres: ${this.genres ?: "No genres available."}"
    }

    private fun getFormattedRunningTime(): String {
        val runtime = this.runtimeMins?.toInt() ?: 0
        return runtime.parseDuration()
    }

    fun getFormattedSummary(): String {
        return "Plot summary: ${this.plot ?: "No plot summary available."}"
    }
}
