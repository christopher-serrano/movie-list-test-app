package com.serranocjm.movielisttestapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.serranocjm.movielisttestapp.R
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

    private lateinit var navController: NavController

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
        setOnBackPressedCallback()
        initValues()
        loadData()
    }

    override fun initValues() {
        super.initValues()
        navController = findNavController()
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
            setUpFields()
        }
    }

    override fun loadData() {
        super.loadData()
        viewModel.getMovieDetail(movieId)
    }

    private fun setUpFields() {
        movie?.let {
            binding.tvMovieTitle.text = it.title
            binding.tvMovieBasicInfo.text = it.getBasicInfo()
            binding.tvMovieSummary.text = it.getFormattedSummary()
            imageLoader.loadWithUrl(
                it.image,
                binding.ivMovieThumbnail,
                R.drawable.ic_movie_thumb_placeholder
            )
        }
    }

    override fun setOnBackPressedCallback() {
        super.setOnBackPressedCallback()
        requireActivity().onBackPressedDispatcher.addCallback(object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    navController.popBackStack()
                }
            })
    }
}
