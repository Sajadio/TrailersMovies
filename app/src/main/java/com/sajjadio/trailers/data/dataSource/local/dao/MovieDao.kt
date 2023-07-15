package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.data.base.MovieLocalDataSource
import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.domain.model.SearchMovieResult
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao : MovieLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addPopularMovies(items: List<PopularMovieEntity>)

    @Query("SELECT * FROM popular_movie_table")
    override fun getAllSavedPopularMovies(): Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addTopRatedMovies(items: List<TopRatedMovieEntity>)

    @Query("SELECT * FROM top_rated_movie_table")
    override fun getAllSavedTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addTrendMovies(items: List<TrendMovieEntity>)

    @Query("SELECT * FROM trend_movie_table")
    override fun getAllSavedTrendMovies(): Flow<List<TrendMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addUpComingMovies(items: List<UpcomingMovieEntity>)

    @Query("SELECT * FROM upcoming_movie_table")
    override fun getAllSavedUpComingMovies(): Flow<List<UpcomingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addSearchMovies(items: List<SearchMovieResult>)

    @Query("SELECT * FROM search_movie_table WHERE original_title LIKE '%' || :query || '%'")
    override fun getAllSavedSearchMovies(query: String?): PagingSource<Int, SearchMovieResult>

    @Query("DELETE FROM search_movie_table")
    override fun deleteAllSavedSearchMovies()
}