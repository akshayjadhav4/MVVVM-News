package com.example.mvvvmnews.ui.db

import androidx.room.TypeConverter
import com.example.mvvvmnews.ui.models.Source

class Converters {

    //converting Source (article source) into string
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }
}