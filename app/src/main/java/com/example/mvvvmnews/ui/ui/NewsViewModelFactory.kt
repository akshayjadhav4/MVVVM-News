package com.example.mvvvmnews.ui.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvvmnews.ui.repository.NewsRepository

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory(val app: Application, val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(app,newsRepository) as T
    }
}