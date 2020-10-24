package com.alkar.cartelera.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


const val API_KEY = "05f20b09e88ebf3a85349e53809e7ff9"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

//https://api.themoviedb.org/3/movie/popular?api_key=05f20b09e88ebf3a85349e53809e7ff9&page=1
//https://api.themoviedb.org/3/movie/497582?api_key=05f20b09e88ebf3a85349e53809e7ff9
//https://image.tmdb.org/t/p/w342/riYInlsq2kf1AWoGm80JQW5dLKp.jpg

object TheMovieDBClient {
    fun getClient (): TheMovieDBInterface {
        val requestInterceptor = Interceptor { chain ->
            //Interceptor solo toma un argumento
            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key",API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder ()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)
    }
}