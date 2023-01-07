package com.serranocjm.movielisttestapp.di

import android.content.Context
import com.serranocjm.movielisttestapp.data.local.database.TheMovieListDatabase
import com.serranocjm.movielisttestapp.data.local.database.dao.MovieDao
import com.serranocjm.movielisttestapp.data.remote.network.ApiClient
import com.serranocjm.movielisttestapp.repository.MovieRepository
import com.serranocjm.movielisttestapp.repository.MovieRepositoryImpl
import com.serranocjm.movielisttestapp.ui.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { ApiClient.invoke() }
}

val repositoryModule = module {
    factory<MovieRepository> { MovieRepositoryImpl() }
}

val dataModule = module {
    single { androidContext().getSharedPreferences("Prefs", Context.MODE_PRIVATE) }
}

val databaseModule = module {

    // DB Binding
    single { TheMovieListDatabase.invoke(androidContext()) }

    fun provideMovieDao(database: TheMovieListDatabase): MovieDao {
        return database.getMovieDao()
    }

    // DAO Binding
    single { provideMovieDao(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel() }
}

val utilsModule = module {
}
