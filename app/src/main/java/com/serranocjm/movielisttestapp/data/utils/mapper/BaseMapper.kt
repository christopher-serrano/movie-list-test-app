package com.serranocjm.movielisttestapp.data.utils.mapper

interface BaseMapper<E, D> {
    fun transform(type: E): D
    fun transformToRepository(type: D): E
}
