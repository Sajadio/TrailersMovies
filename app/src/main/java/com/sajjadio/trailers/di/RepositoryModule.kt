package com.sajjadio.trailers.di

import com.sajjadio.trailers.domain.repository.MovieRepository
import com.sajjadio.trailers.data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepository(
        movieRepository: MovieRepositoryImpl
    ): MovieRepository

}