package com.sajjadio.trailers.data.base


import androidx.paging.PagingSource
import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.domain.model.SearchMovieResult
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    suspend fun addPopularMovies(items: List<PopularMovieEntity>)
    fun getAllSavedPopularMovies(): Flow<List<PopularMovieEntity>>
    suspend fun addTopRatedMovies(items: List<TopRatedMovieEntity>)
    fun getAllSavedTopRatedMovies(): Flow<List<TopRatedMovieEntity>>
    suspend fun addTrendMovies(items: List<TrendMovieEntity>)
    fun getAllSavedTrendMovies(): Flow<List<TrendMovieEntity>>
    suspend fun addUpComingMovies(items: List<UpcomingMovieEntity>)
    fun getAllSavedUpComingMovies(): Flow<List<UpcomingMovieEntity>>
    suspend fun addSearchMovies(items: List<SearchMovieResult>)
    fun getAllSavedSearchMovies(query: String?): PagingSource<Int, SearchMovieResult>
    fun deleteAllSavedSearchMovies()
}