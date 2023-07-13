package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.SearchMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TopRatedMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.TrendMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPopularMovies(items:List<PopularMovieEntity>)

    @Query("SELECT * FROM popular_movie_table")
    fun getAllSavedPopularMovies(): Flow<List<PopularMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopRatedMovies(items:List<TopRatedMovieEntity>)

    @Query("SELECT * FROM top_rated_movie_table")
    fun getAllSavedTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrendMovies(items:List<TrendMovieEntity>)

    @Query("SELECT * FROM trend_movie_table")
    fun getAllSavedTrendMovies(): Flow<List<TrendMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUpComingMovies(items:List<UpcomingMovieEntity>)

    @Query("SELECT * FROM upcoming_movie_table")
    fun getAllSavedUpComingMovies(): Flow<List<UpcomingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSearchMovies(items:List<SearchMovieEntity>)

    @Query("SELECT * FROM search_movie_table")
    fun getAllSavedSearchMovies(): Flow<List<SearchMovieEntity>>
}