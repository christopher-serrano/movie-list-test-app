package com.serranocjm.movielisttestapp.data.db.dao

import androidx.room.*
import com.serranocjm.movielisttestapp.data.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: MovieEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: MovieEntity)

    @Transaction
    suspend fun upsert(entity: MovieEntity) {
        val id: Long = insert(entity)
        if (id == -1L) {
            update(entity)
        }
    }

    @Query("SELECT * FROM movies")
    suspend fun getMovieList(): List<MovieEntity>?

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieDetail(id: String): MovieEntity?

    @Query("DELETE FROM movies")
    suspend fun deleteMovieList()

    @Transaction
    suspend fun purge() {
        val movies = getMovieList()
        if (movies != null) {
            deleteMovieList()
        }
    }
}