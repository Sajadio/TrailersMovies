package com.sajjadio.trailers.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sajjadio.trailers.data.dataSource.local.converter.Converter
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDetailsDao
import com.sajjadio.trailers.data.dataSource.local.entites.MovieDetailsEntity
import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.SearchMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.domain.model.MovieDetails


@TypeConverters(Converter::class)
@Database(
    version = 1,
    entities = [
        MovieDetailsEntity::class,
        PopularMovieEntity::class,
        TopRatedMovieEntity::class,
        TrendMovieEntity::class,
        UpcomingMovieEntity::class,
        SearchMovieEntity::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getMovieDetailsDao(): MovieDetailsDao
    abstract fun getMovieDao(): MovieDao
}