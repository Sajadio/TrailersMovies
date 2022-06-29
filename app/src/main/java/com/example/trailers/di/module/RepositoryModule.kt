package com.example.trailers.di.module

import com.example.trailers.data.repository.common.CommonRepoImpl
import com.example.trailers.data.repository.common.CommonRepository
import com.example.trailers.data.repository.genres.GenresRepoImpl
import com.example.trailers.data.repository.genres.GenresRepository
import com.example.trailers.data.repository.home.HomeRepoImpl
import com.example.trailers.data.repository.home.HomeRepository
import com.example.trailers.data.repository.movie.MovieRepoImpl
import com.example.trailers.data.repository.movie.MovieRepository
import com.example.trailers.data.repository.search.SearchRepoImpl
import com.example.trailers.data.repository.search.SearchRepository
import com.example.trailers.data.repository.similar.SimilarRepoImpl
import com.example.trailers.data.repository.similar.SimilarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(
        homeRepo: HomeRepoImpl,
    ): HomeRepository

    @Binds
    abstract fun bindMovieRepository(
        movieRepoImpl: MovieRepoImpl,
    ): MovieRepository

    @Binds
    abstract fun bindSimilarRepository(
       similarRepoImpl: SimilarRepoImpl
    ): SimilarRepository

    @Binds
    abstract fun bindCommonRepository(
        commonRepoImpl: CommonRepoImpl,
    ): CommonRepository

    @Binds
    abstract fun bindGenresRepository(
        genresRepoImpl: GenresRepoImpl,
    ): GenresRepository

    @Binds
    abstract fun bindSearchRepository(
        searchRepoImpl: SearchRepoImpl,
    ): SearchRepository


}