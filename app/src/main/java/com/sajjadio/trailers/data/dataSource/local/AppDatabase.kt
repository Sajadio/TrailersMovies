package com.sajjadio.trailers.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.sajjadio.trailers.data.dataSource.local.converter.Converter
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.domain.model.MovieDetails


@TypeConverters(Converter::class)
@Database(version = 1, entities = [MovieDetails::class])
abstract class AppDatabase :RoomDatabase(){
    abstract fun getMovieDao(): MovieDao
}