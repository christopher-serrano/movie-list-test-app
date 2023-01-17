package com.serranocjm.movielisttestapp.data.local.database.dao

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.serranocjm.movielisttestapp.base.BaseITTest
import com.serranocjm.movielisttestapp.data.local.database.TheMovieListDatabase
import com.serranocjm.movielisttestapp.data.remote.model.Movie
import com.serranocjm.movielisttestapp.data.utils.mapper.MovieMapperLocal
import com.serranocjm.movielisttestapp.utils.parseArray
import com.serranocjm.movielisttestapp.utils.typeToken
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class MovieDaoTest : BaseITTest() {

    private lateinit var movieDao: MovieDao
    private lateinit var database: TheMovieListDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, TheMovieListDatabase::class.java).build()
        movieDao = database.getMovieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insert_movie_entity_test(): Unit = runBlocking {
        val mapper = MovieMapperLocal()
        val json = getJson("movie_detail.json")
        val movie: Movie = parseArray(json, typeToken<Movie>().type)

        // assert movie object is not null
        assertNotNull(movie)

        // test database insertion
        val id = movieDao.insert(mapper.transformToRepository(movie))

        // assert the entity is inserted
        assert(id != -1L)
    }
}
