package com.serranocjm.movielisttestapp.data.local.database

import android.content.Context
import androidx.room.*
import com.serranocjm.movielisttestapp.data.local.database.converter.MovieDataConverter
import com.serranocjm.movielisttestapp.data.local.database.dao.MovieDao
import com.serranocjm.movielisttestapp.data.local.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1
)
@TypeConverters(MovieDataConverter::class)
abstract class TheMovieListDatabase : RoomDatabase() {

    // Basic DAOs
    abstract fun getMovieDao(): MovieDao

    companion object {

        @Volatile
        private var instance: TheMovieListDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TheMovieListDatabase::class.java,
                "themovielist.db"
            ).fallbackToDestructiveMigration().build()
    }
}
