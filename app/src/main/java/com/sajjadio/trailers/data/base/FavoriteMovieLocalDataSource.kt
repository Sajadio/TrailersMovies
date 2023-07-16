package com.sajjadio.trailers.data.base


import com.sajjadio.trailers.data.dataSource.local.entites.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

interface FavoriteMovieLocalDataSource {
    suspend fun addMovie(favoriteMovie: FavoriteMovieEntity): Long
    suspend fun deleteMovie(movieId:Int)
    suspend fun deleteAllMovies()
    fun getAllSavedMovies(): Flow<List<FavoriteMovieEntity>>
    fun checkIsMovieSaved(movieId: Int): Flow<Boolean>
}