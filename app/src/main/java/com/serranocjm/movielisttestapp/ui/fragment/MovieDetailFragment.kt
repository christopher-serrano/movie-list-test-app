package com.serranocjm.movielisttestapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.databinding.FragmentMovieDetailBinding
import com.serranocjm.movielisttestapp.ui.viewmodel.MovieViewModel
import com.serranocjm.movielisttestapp.utils.toastLong
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieDetailFragment : BaseFragment(), KoinComponent {

    private val viewModel: MovieViewModel by inject()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val args: MovieDetailFragmentArgs by navArgs()

    private var movieId: String = ""
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        observeViewModel()
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initValues()
        loadData()
    }

    override fun initValues() {
        super.initValues()
        movieId = args.movieId
    }

    override fun observeViewModel() = viewModel.run {
        onError.observe(viewLifecycleOwner) {
            requireActivity().toastLong(it)
        }
        loadingState.observe(viewLifecycleOwner) {
            binding.clLoading.isVisible = it
        }
        movieDetail.observe(viewLifecycleOwner) {
            movie = it
        }
    }

    override fun loadData() {
        super.loadData()
        viewModel.getMovieDetail(movieId)
    }

    private fun setUpFields() {
    }
}
