package com.sajjadio.trailers.data.dataSource.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sajjadio.trailers.data.dataSource.local.entites.GenreOfMovie

class Converter {
    @TypeConverter
    fun fromGenres(genreOfMovies: List<GenreOfMovie>): String {
        val gson = Gson()
        return gson.toJson(genreOfMovies)
    }

    @TypeConverter
    fun toGenres(genresString: String): List<GenreOfMovie> {
        val gson = Gson()
        val type = object : TypeToken<List<GenreOfMovie>>() {}.type
        return gson.fromJson(genresString, type)
    }
}