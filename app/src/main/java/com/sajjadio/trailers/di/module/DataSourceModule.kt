package com.sajjadio.trailers.di.module

import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.dataSource.remote.MovieApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsMovieRemoteDataSource(
        movieApiService: MovieApiService
    ): MovieRemoteDataSource
}