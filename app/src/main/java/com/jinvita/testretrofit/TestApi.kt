package com.jinvita.testretrofit

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface TestApi {
    @GET("posts")
    fun getPostList(): Call<PostsData>

    @GET("posts")
    suspend fun getPostList2(): Response<PostsData>

    @GET("posts")
    fun getPost(
        @Query("id") postId: Int
    ): Call<PostsData>

    @GET("posts")
    suspend fun getPost2(
        @Query("id") postId: Int
    ): Response<PostsData>

    @GET("posts/{postId}")
    fun getPost3(
        @Path("postId") postId: Int
    ): Call<PostData>

    @GET("posts/{postId}")
    suspend fun getPost4(
        @Path("postId") postId: Int
    ): Response<PostData>
}