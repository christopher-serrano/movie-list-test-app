package com.serranocjm.movielisttestapp.repository

import android.content.Context
import com.nhaarman.mockitokotlin2.verify
import com.serranocjm.movielisttestapp.base.BaseUTTest
import com.serranocjm.movielisttestapp.data.remote.model.MovieListResponse
import com.serranocjm.movielisttestapp.data.remote.network.Endpoints
import com.serranocjm.movielisttestapp.di.networkModule
import com.serranocjm.movielisttestapp.di.repositoryModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNotNull

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class MovieRepositoryTest : BaseUTTest(), KoinTest {

    @Before
    override fun setUp() {
        super.setUp()
        startKoin {
            androidContext(Mockito.mock(Context::class.java))
            modules(networkModule, repositoryModule)
        }
    }

    @Test
    fun get_movie_list_test() = runTest(UnconfinedTestDispatcher()) {
        // Mock response classes
        val movieListResponsemock = Mockito.mock(MovieListResponse::class.java)

        // Mock network classes
        val endpointsMock = Mockito.mock(Endpoints::class.java)

        // Enqueue call and set response
        mockNetworkResponseWithFileContent("movie_response.json", 200)

        // Verify response and assert
        verify(1) { endpointsMock.getMovieList() }
        assertNotNull(movieListResponsemock)
    }
}
