package com.serranocjm.movielisttestapp.repository

import com.serranocjm.movielisttestapp.data.local.database.dao.MovieDao
import com.serranocjm.movielisttestapp.data.local.entity.MovieEntity
import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.data.remote.network.ApiClient
import com.serranocjm.movielisttestapp.data.utils.Result
import com.serranocjm.movielisttestapp.data.utils.mapper.MovieMapperLocal
import com.serranocjm.movielisttestapp.utils.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MovieRepositoryImpl : MovieRepository, KoinComponent {

    private val api = ApiClient.invoke()
    private val dao: MovieDao by inject()

    override suspend fun fetchMovieList(fromRemote: Boolean): Result<List<Movie>> =
        withContext(Dispatchers.IO) {
            when {
                fromRemote -> {
                    val response = api.getMovieList()
                    if (response.isSuccessful) {
                        val data = response.body()
                        if (data != null) {
                            // persist the list
                            persistMovieList(data.items)
                            // prepare result
                            Result.Success(data.items ?: mutableListOf())
                        } else {
                            // Result is null
                            Result.Success(null)
                        }
                    } else {
                        // Return error
                        Result.Error(ApiException("Data error."))
                    }
                }
                else -> {
                    val localData = dao.getMovieList()
                    if (localData == null) {
                        Result.Success(null)
                    } else {
                        val mapper = MovieMapperLocal()
                        val movieList = mutableListOf<Movie>()
                        localData.forEach { entity ->
                            movieList.add(mapper.transform(entity))
                        }
                        Result.Success(movieList)
                    }
                }
            }
        }

    override suspend fun fetchMovieDetail(id: String): Result<Movie> = withContext(Dispatchers.IO) {
        val mapper = MovieMapperLocal()
        val result = dao.getMovieDetail(id)
        if (result != null) {
            Result.Success(mapper.transform(result))
        } else {
            Result.Success(null)
        }
    }

    private suspend fun persistMovieList(list: List<Movie>?) {
        val mapper = MovieMapperLocal()
        val entityList = mutableListOf<MovieEntity>()
        list?.forEach { movie ->
            entityList.add(mapper.transformToRepository(movie))
        }
        dao.saveMovieList(entityList)
    }
}
