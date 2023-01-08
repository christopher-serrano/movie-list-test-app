package com.serranocjm.movielisttestapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.serranocjm.movielisttestapp.R
import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.databinding.FragmentMovieListBinding
import com.serranocjm.movielisttestapp.ui.adapter.base.DynamicAdapter
import com.serranocjm.movielisttestapp.ui.adapter.base.ItemModel
import com.serranocjm.movielisttestapp.ui.adapter.item.model.MovieItemModel
import com.serranocjm.movielisttestapp.ui.adapter.type.factory.MovieTypeFactoryImpl
import com.serranocjm.movielisttestapp.ui.viewmodel.MovieViewModel
import com.serranocjm.movielisttestapp.utils.toastLong
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
    private lateinit var movieAdapter: DynamicAdapter

    // lists
    var movieModelList: List<Movie> = mutableListOf()

    // fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMovieAdapter(movieModelList)
        loadData()
    }

    override fun onResume() {
        super.onResume()
        initValues()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // base

    override fun initValues() {
        navController = findNavController()
    }

    override fun loadData() {
        viewModel.fetchMovieList()
    }

    override fun observeViewModel() = viewModel.run {
        loadingState.observe(viewLifecycleOwner) {
            binding.clLoading.isVisible = it
        }
        movieList.observe(viewLifecycleOwner) {
            movieModelList = it ?: mutableListOf()
            initMovieAdapter()
        }
        onError.observe(viewLifecycleOwner) {
            requireActivity().toastLong(it)
        }
    }

    // adapter

    // populate the adapter
    @SuppressLint("NotifyDataSetChanged")
    private fun initMovieAdapter() {
        movieAdapter.apply {
            submitList(getMovieListForAdapter(movieModelList))
            notifyDataSetChanged()
        }
    }

    // initial adapter setup
    private fun setUpMovieAdapter(movieList: List<Movie>?) {
        val adapter = getDynamicAdapter(movieList, onMovieItemClick)
        val manager = LinearLayoutManager(requireActivity())

        movieAdapter = adapter
        binding.rvMovieList.adapter = movieAdapter
        binding.rvMovieList.layoutManager = manager
    }

    private fun getDynamicAdapter(
        movieList: List<Movie>?,
        onItemClick: (ItemModel, String) -> Unit
    ): DynamicAdapter {
        return DynamicAdapter(
            typeFactory = MovieTypeFactoryImpl(),
            items = getMovieListForAdapter(movieList?.toMutableList()),
            onClick = onItemClick,
            imageLoader = imageLoader
        )
    }

    private fun getMovieListForAdapter(list: List<Movie>?): List<ItemModel> {
        val result = mutableListOf<ItemModel>()
        list?.forEach { movie ->
            result.add(MovieItemModel(movie))
        }
        return result
    }

    private var onMovieItemClick: (ItemModel, String) -> Unit = { item, action ->
        val movie: MovieItemModel = item as MovieItemModel
        when (action) {
            "goto_movie_detail" -> {
                movie.model.id?.let {
                    navController.navigate(
                        MovieListFragmentDirections.actionMovieListToMovieDetail(it)
                    )
                }
            }
            else -> {}
        }
    }
}
