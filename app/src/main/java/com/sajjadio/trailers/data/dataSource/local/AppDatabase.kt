package com.sajjadio.trailers.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sajjadio.trailers.data.dataSource.local.converter.Converter
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.FavoriteMovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.SearchMovieRemoteKeyDao
import com.sajjadio.trailers.data.dataSource.local.entites.MovieDetailsEntity
import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey
import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.domain.model.SearchMovieResult


@TypeConverters(Converter::class)
@Database(
    version = 1,
    entities = [
        MovieDetailsEntity::class,
        PopularMovieEntity::class,
        TopRatedMovieEntity::class,
        TrendMovieEntity::class,
        UpcomingMovieEntity::class,
        SearchMovieResult::class,
        SearchRemoteKey::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getFavoriteMovieDao(): FavoriteMovieDao
    abstract fun getMovieDao(): MovieDao
    abstract fun getSearchMovieRemoteKeyDao(): SearchMovieRemoteKeyDao
}