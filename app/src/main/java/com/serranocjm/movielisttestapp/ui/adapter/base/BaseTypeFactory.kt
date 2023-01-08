package com.serranocjm.movielisttestapp.ui.adapter.base

import android.view.View
import com.serranocjm.movielisttestapp.utils.ImageLoader

interface BaseTypeFactory {
    fun holder(type: Int, view: View, imageLoader: ImageLoader): DynamicAdapterViewHolder<*>
}
