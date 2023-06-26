package com.jinvita.testretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TestApi {
    @GET("posts")
    fun getPosts(): Call<TestData>

    @GET("posts")
    suspend fun getPosts2(): Response<TestData>

    @GET("posts")
    fun getEachPost(
        @Query("id") id: Int
    ): Call<TestData>

    @GET("posts")
    suspend fun getEachPost2(
        @Query("id") id: Int
    ): Response<TestData>
}