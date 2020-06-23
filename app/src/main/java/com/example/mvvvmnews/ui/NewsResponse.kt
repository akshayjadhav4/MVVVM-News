package com.example.mvvvmnews.ui

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)