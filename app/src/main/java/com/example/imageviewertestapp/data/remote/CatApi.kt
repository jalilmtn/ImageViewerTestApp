package com.example.imageviewertestapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query


interface CatApi {

    @GET("images/search")
    suspend fun getCats(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<CatDto>


}