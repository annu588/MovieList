package com.app.moviedemo.data.api

import com.app.moviedemo.utils.CONSTRAINTS

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CONSTRAINTS.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    private val httpClient: OkHttpClient =
        OkHttpClient.Builder().connectTimeout(CONSTRAINTS.TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CONSTRAINTS.TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(CONSTRAINTS.TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                val ongoing: Request.Builder = chain.request().newBuilder()
                ongoing.addHeader("Accept", "application/json")
                ongoing.addHeader("Content-Type", "application/json")

                chain.proceed(ongoing.build())
            }).addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()


    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}