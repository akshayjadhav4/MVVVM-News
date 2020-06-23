package com.example.mvvvmnews.ui.api

import retrofit2.http.GET


//used to define request
interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews()
}