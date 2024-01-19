package com.ruslanlialko.compose_project.data.remote

import com.ruslanlialko.compose_project.data.dto.ExampleDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ExampleApi {

    @GET("/some/data")
    suspend fun getExampleData(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ExampleDto

}