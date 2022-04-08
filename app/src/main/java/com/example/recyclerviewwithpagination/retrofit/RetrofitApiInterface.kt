package com.example.recyclerviewwithpagination.retrofit

import com.example.recyclerviewwithpagination.model.NetworkMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiInterface {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s")s:String,
        @Query("page")page:Int,
        @Query("apiKey")apiKey:String
    ): Response<NetworkMovieResponse>
}