package com.serranocjm.movielisttestapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.serranocjm.movielisttestapp.R
import com.serranocjm.movielisttestapp.databinding.FragmentMovieListBinding
import com.serranocjm.movielisttestapp.ui.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MovieListFragment : BaseFragment(), KoinComponent {

    // viewmodel
    private val viewModel: MovieViewModel by viewModel()

    // binding
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    // navigation
    private lateinit var navController: NavController

    // adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }
}
