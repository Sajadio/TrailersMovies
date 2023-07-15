package com.sajjadio.trailers.di

import com.sajjadio.trailers.data.base.FavoriteMovieLocalDataSource
import com.sajjadio.trailers.data.base.MovieLocalDataSource
import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.base.SearchMovieLocalDataSource
import com.sajjadio.trailers.data.dataSource.local.dao.FavoriteMovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.MovieDao
import com.sajjadio.trailers.data.dataSource.local.dao.SearchMovieRemoteKeyDao
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindMovieRemoteDataSource(
        movieApiService: MovieApiService
    ): MovieRemoteDataSource

    @Binds
    abstract fun bindMovieLocalDataSource(
        movieDao: MovieDao
    ): MovieLocalDataSource

    @Binds
    abstract fun bindSearchMovieLocalDataSource(
        keyDao: SearchMovieRemoteKeyDao
    ): SearchMovieLocalDataSource

    @Binds
    abstract fun bindFavoriteMovieLocalDataSource(
        favoriteMovieDao: FavoriteMovieDao
    ): FavoriteMovieLocalDataSource
}