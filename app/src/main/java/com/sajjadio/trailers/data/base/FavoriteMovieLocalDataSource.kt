package com.sajjadio.trailers.data.base


import com.sajjadio.trailers.data.dataSource.local.entites.MovieDetailsEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieLocalDataSource {
    suspend fun addMovie(movieDetails: MovieDetailsEntity): Long
    suspend fun deleteMovie(movieDetails: MovieDetailsEntity)
    suspend fun deleteAllMovies()
    fun getAllSavedMovies(): Flow<List<MovieDetailsEntity>>
    suspend fun checkIsMovieSaved(movieId: Int): Boolean
}