package com.example.mvvvmnews.ui.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvvmnews.ui.models.Article

@Database(entities = [Article::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

}