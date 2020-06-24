package com.example.mvvvmnews.ui.repository

import com.example.mvvvmnews.ui.api.RetrofitInstance
import com.example.mvvvmnews.ui.db.ArticleDatabase


//gets data from db and response
class NewsRepository(val db: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)
}