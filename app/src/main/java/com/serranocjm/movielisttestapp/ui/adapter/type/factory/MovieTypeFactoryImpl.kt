package com.serranocjm.movielisttestapp.ui.adapter.type.factory

import android.view.View
import com.serranocjm.movielisttestapp.R
import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.movielisttestapp.ui.adapter.holder.ItemMovieHolder
import com.serranocjm.movielisttestapp.utils.ImageLoader

class MovieTypeFactoryImpl : MovieTypeFactory {
    override fun typeMovie(type: Movie): Int = R.layout.item_movie

    override fun holder(
        type: Int,
        view: View,
        imageLoader: ImageLoader
    ): DynamicAdapterViewHolder<*> {
        return when (type) {
            R.layout.item_movie -> ItemMovieHolder(view, imageLoader)
            else -> throw RuntimeException("Illegal view type.")
        }
    }
}
