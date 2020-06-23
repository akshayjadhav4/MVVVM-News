package com.example.mvvvmnews.ui.db

import androidx.room.TypeConverter
import com.example.mvvvmnews.ui.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {

    }
}