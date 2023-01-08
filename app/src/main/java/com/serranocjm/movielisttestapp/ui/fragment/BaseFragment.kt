package com.serranocjm.movielisttestapp.ui.fragment

import androidx.fragment.app.Fragment
import com.serranocjm.movielisttestapp.utils.ImageLoader
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseFragment : Fragment(), KoinComponent {

    protected val imageLoader: ImageLoader by inject()

    open fun initValues() {}
    open fun observeViewModel() {}
    open fun loadData() {}
    open fun setUpDynamicAdapter() {}
}
