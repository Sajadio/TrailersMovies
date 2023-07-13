package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.data.dataSource.local.entites.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movieDetails: MovieDetailsEntity): Long

    @Delete
    suspend fun deleteMovie(movieDetails: MovieDetailsEntity)

    @Query("DELETE FROM movie_details_table")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM movie_details_table")
    fun getAllSavedMovies(): Flow<List<MovieDetailsEntity>>

    @Query("SELECT EXISTS (SELECT * FROM movie_details_table WHERE id = :movieId)")
    suspend fun checkIsMovieSaved(movieId: Int): Boolean
}