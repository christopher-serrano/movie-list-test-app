package com.serranocjm.movielisttestapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.data.utils.Result
import com.serranocjm.movielisttestapp.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieViewModel : BaseViewModel(), KoinComponent {

    private val movieRepository: MovieRepository by inject()

    // Live Data
    val movieList = MutableLiveData<List<Movie>?>()
    val movieDetail = MutableLiveData<Movie?>()

    private fun fetchMovieList() {
        viewModelScope.launch {
            fetchMovieListAsync()
        }
    }

    fun getMovieDetail(id: String) {
        viewModelScope.launch {
            getMovieDetailAsync(id)
        }
    }

    // Get the movie from remote source
    private suspend fun getMovieListAsync() {
        loadingState.postValue(true)
        val result = withContext(Dispatchers.IO) {
            movieRepository.fetchMovieList(true)
        }
        when (result) {
            is Result.Success -> {
                loadingState.postValue(false)
                if (result.data != null) {
                    movieList.postValue(result.data)
                }
            }
            is Result.Error -> {
                onError.postValue(result.exception.message)
            }
            is Result.Loading -> {}
        }
    }

    // get the movie list from onboard storage
    private suspend fun fetchMovieListAsync() {
        loadingState.postValue(true)
        val result = withContext(Dispatchers.IO) {
            movieRepository.fetchMovieList(false)
        }
        when (result) {
            is Result.Success -> {
                loadingState.postValue(false)
                if (result.data != null) {
                    movieList.postValue(result.data)
                } else {
                    getMovieListAsync()
                }
            }
            is Result.Error -> {
                onError.postValue(result.exception.message)
            }
            is Result.Loading -> {}
        }
    }

    // fetch the movie detail from onboard storage
    private suspend fun getMovieDetailAsync(id: String) {
        loadingState.postValue(true)
        val result = withContext(Dispatchers.IO) {
            movieRepository.fetchMovieDetail(id)
        }
        when (result) {
            is Result.Success -> {
                loadingState.postValue(false)
                movieDetail.postValue(result.data)
            }
            is Result.Error -> {
                loadingState.postValue(false)
                onError.postValue(result.exception.message)
            }
            is Result.Loading -> {}
        }
    }
}
