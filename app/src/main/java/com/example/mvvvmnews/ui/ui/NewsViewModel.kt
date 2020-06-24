package com.example.mvvvmnews.ui.ui

import androidx.lifecycle.ViewModel
import com.example.mvvvmnews.ui.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
):ViewModel() {
}