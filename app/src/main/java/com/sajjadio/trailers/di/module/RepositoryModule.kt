package com.sajjadio.trailers.di.module

import com.sajjadio.trailers.data.repository.common.CommonRepoImpl
import com.sajjadio.trailers.data.repository.common.CommonRepository
import com.sajjadio.trailers.data.repository.genres.GenresRepoImpl
import com.sajjadio.trailers.data.repository.genres.GenresRepository
import com.sajjadio.trailers.data.repository.home.HomeRepoImpl
import com.sajjadio.trailers.data.repository.home.HomeRepository
import com.sajjadio.trailers.data.repository.movie.MovieRepoImpl
import com.sajjadio.trailers.data.repository.movie.MovieRepository
import com.sajjadio.trailers.data.repository.search.SearchRepoImpl
import com.sajjadio.trailers.data.repository.search.SearchRepository
import com.sajjadio.trailers.data.repository.similar.SimilarRepoImpl
import com.sajjadio.trailers.data.repository.similar.SimilarRepository
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