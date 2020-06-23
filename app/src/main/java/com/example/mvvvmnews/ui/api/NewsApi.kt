package com.example.mvvvmnews.ui.api

import com.example.mvvvmnews.ui.util.Constants.Companion.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


//used to define request
interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "in",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    )
}