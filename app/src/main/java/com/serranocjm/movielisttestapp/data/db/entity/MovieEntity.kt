package com.serranocjm.movielisttestapp.data.db.entity

import androidx.room.Entity
import com.serranocjm.movielisttestapp.data.remote.model.Director
import com.serranocjm.movielisttestapp.data.remote.model.Genre
import com.serranocjm.movielisttestapp.data.remote.model.Star

@Entity(tableName = "movies")
data class MovieEntity(
    val id: String?,
    val title: String?,
    val fullTitle: String?,
    val year: String?,
    val releaseState: String?,
    val image: String?,
    val runtimeMins: String?,
    val runtimeStr: String?,
    val plot: String?,
    val contentRating: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
    val metacriticRating: String?,
    val genres: String?,
    val genreList: List<Genre>?,
    val directors: String?,
    val directorList: List<Director>?,
    val stars: String?,
    val starList: List<Star>?
) : BaseEntity()
