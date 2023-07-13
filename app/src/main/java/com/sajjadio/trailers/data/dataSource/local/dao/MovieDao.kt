package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDetails: MovieDetails):Long

    @Delete
    suspend fun deleteMovie(movieDetails: MovieDetails)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_table")
     fun getAllSavedMovies(): Flow<List<MovieDetails>>

    @Query("SELECT EXISTS (SELECT * FROM movie_table WHERE original_title = :title)")
    suspend fun checkISMovieSavedByTitle(title: String): Boolean
}