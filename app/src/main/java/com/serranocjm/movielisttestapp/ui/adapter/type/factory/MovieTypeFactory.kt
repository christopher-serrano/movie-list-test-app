package com.serranocjm.movielisttestapp.ui.adapter.type.factory

import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.ui.adapter.base.BaseTypeFactory

interface MovieTypeFactory : BaseTypeFactory {
    fun typeMovie(type: Movie): Int
}
