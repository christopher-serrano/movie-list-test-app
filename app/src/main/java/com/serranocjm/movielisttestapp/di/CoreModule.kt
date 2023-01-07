package com.serranocjm.movielisttestapp.di

import org.koin.core.context.GlobalContext.loadKoinModules

object CoreModule {
    private val modules =
        listOf(
            networkModule,
            repositoryModule,
            databaseModule,
            viewModelModule,
            dataModule,
            utilsModule
        )

    fun init() = loadKoinModules(modules)
}
