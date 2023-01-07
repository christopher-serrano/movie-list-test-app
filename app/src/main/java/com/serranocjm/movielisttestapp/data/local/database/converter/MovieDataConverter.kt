package com.serranocjm.movielisttestapp.data.local.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.serranocjm.movielisttestapp.data.remote.model.Director
import com.serranocjm.movielisttestapp.data.remote.model.Genre
import com.serranocjm.movielisttestapp.data.remote.model.Star

object MovieDataConverter {

    @TypeConverter
    @JvmStatic
    fun fromGenreList(list: List<Genre>): String? {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun toGenreList(list: String?): List<Genre>? {
        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromDirectorList(list: List<Director>): String? {
        val type = object : TypeToken<List<Director>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun toDirectorList(list: String?): List<Director>? {
        val type = object : TypeToken<List<Director>>() {}.type
        return Gson().fromJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun fromStarList(list: List<Star>): String? {
        val type = object : TypeToken<List<Star>>() {}.type
        return Gson().toJson(list, type)
    }

    @TypeConverter
    @JvmStatic
    fun toStarList(list: String?): List<Star>? {
        val type = object : TypeToken<List<Star>>() {}.type
        return Gson().fromJson(list, type)
    }
}
