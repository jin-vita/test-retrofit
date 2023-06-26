package com.jinvita.testretrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object TestApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpInterceptor())
    }

    val api: TestApi by lazy {
        retrofit.build().create(TestApi::class.java)
    }

    private fun okHttpInterceptor(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        if (App.isHttpLog) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        clientBuilder.connectTimeout(5, TimeUnit.SECONDS)
        clientBuilder.readTimeout(5, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(5, TimeUnit.SECONDS)
        return clientBuilder.build()
    }
}