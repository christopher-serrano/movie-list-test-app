package com.serranocjm.movielisttestapp.data.utils.mapper

import com.serranocjm.movielisttestapp.data.local.entity.MovieEntity
import com.serranocjm.movielisttestapp.data.remote.model.Movie

class MovieMapperLocal : BaseMapper<MovieEntity, Movie> {
    override fun transform(type: MovieEntity): Movie {
        return Movie(
            type.id,
            type.title,
            type.fullTitle,
            type.year,
            type.releaseState,
            type.image,
            type.runtimeMins,
            type.runtimeStr,
            type.plot,
            type.contentRating,
            type.imDbRating,
            type.imDbRatingCount,
            type.metacriticRating,
            type.genres,
            type.genreList,
            type.directors,
            type.directorList,
            type.stars,
            type.starList
        )
    }

    override fun transformToRepository(type: Movie): MovieEntity {
        return MovieEntity(
            type.id,
            type.title,
            type.fullTitle,
            type.year,
            type.releaseState,
            type.image,
            type.runtimeMins,
            type.runtimeStr,
            type.plot,
            type.contentRating,
            type.imDbRating,
            type.imDbRatingCount,
            type.metacriticRating,
            type.genres,
            type.genreList,
            type.directors,
            type.directorList,
            type.stars,
            type.starList
        )
    }
}
