package com.serranocjm.movielisttestapp.ui.adapter.item.model

import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.ui.adapter.base.BaseTypeFactory
import com.serranocjm.movielisttestapp.ui.adapter.base.ItemModel
import com.serranocjm.movielisttestapp.ui.adapter.type.factory.MovieTypeFactory

class MovieItemModel(val model: Movie) : ItemModel() {
    override fun type(typeFactory: BaseTypeFactory): Int {
        return (typeFactory as MovieTypeFactory).typeMovie(model)
    }
}
