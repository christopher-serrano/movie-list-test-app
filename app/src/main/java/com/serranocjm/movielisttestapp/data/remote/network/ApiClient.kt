package com.serranocjm.movielisttestapp.data.remote.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.serranocjm.movielisttestapp.BuildConfig
import com.serranocjm.movielisttestapp.data.remote.network.interceptors.RequestInterceptorImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        operator fun invoke(): Endpoints {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) {
                            HttpLoggingInterceptor.Level.BODY
                        } else {
                            HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
                .addInterceptor(RequestInterceptorImpl())
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("url")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Endpoints::class.java)
        }
    }
}
