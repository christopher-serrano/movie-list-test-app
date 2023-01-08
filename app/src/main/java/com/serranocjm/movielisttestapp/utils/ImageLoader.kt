package com.serranocjm.movielisttestapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import org.koin.core.component.KoinComponent

/**
 * Custom image loader using Glide, to be used with DI.
 * Gist at https://gist.github.com/christopher-serrano/cd3b424c299141f969130b0b41d8a72f
 */
class ImageLoader(private val context: Context) : KoinComponent {
    fun loadWithUrl(url: String?, targetView: ImageView, placeholder: Int) {
        Glide.with(context)
            .load(url)
            .placeholder(placeholder)
            .error(placeholder)
            .fallback(placeholder)
            .into(targetView)
    }
}
