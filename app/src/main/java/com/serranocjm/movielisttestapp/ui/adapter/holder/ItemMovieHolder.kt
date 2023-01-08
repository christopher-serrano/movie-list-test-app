package com.serranocjm.movielisttestapp.ui.adapter.holder

import android.view.View
import com.serranocjm.movielisttestapp.databinding.ItemMovieBinding
import com.serranocjm.movielisttestapp.ui.adapter.base.DynamicAdapterViewHolder
import com.serranocjm.movielisttestapp.ui.adapter.base.ItemModel
import com.serranocjm.movielisttestapp.ui.adapter.item.model.MovieItemModel
import com.serranocjm.movielisttestapp.utils.ImageLoader
import com.serranocjm.movielisttestapp.utils.setOneOffClickListener

class ItemMovieHolder(val view: View, imageLoader: ImageLoader) :
    DynamicAdapterViewHolder<MovieItemModel>(view, imageLoader) {

    private var binding = ItemMovieBinding.bind(itemView)

    override fun bind(item: MovieItemModel, position: Int, onClick: (ItemModel, String) -> Unit) {
        binding.tvMovieTitle.text = item.model.title
        binding.tvMovieYear.text = item.model.year
        binding.root.setOneOffClickListener {
            onClick.invoke(item, "goto_movie_detail")
        }
    }
}