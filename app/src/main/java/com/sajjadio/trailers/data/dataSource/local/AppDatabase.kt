package com.sajjadio.trailers.data.dataSource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.FavoriteMovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.SearchMovieRemoteKeyDao
import com.sajjadio.trailers.data.dataSource.local.entites.FavoriteMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey
import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.domain.model.SearchMovieResult


@Database(
    version = 1,
    entities = [
        FavoriteMovieEntity::class,
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