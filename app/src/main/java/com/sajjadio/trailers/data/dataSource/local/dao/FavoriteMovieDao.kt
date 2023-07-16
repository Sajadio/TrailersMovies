package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.data.base.FavoriteMovieLocalDataSource
import com.sajjadio.trailers.data.dataSource.local.entites.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteMovieDao : FavoriteMovieLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addMovie(favoriteMovie: FavoriteMovieEntity): Long

    @Query("DELETE FROM favorite_movie_table WHERE id = :movieId")
    override suspend fun deleteMovie(movieId:Int)

    @Query("DELETE FROM favorite_movie_table")
    override suspend fun deleteAllMovies()

    @Query("SELECT * FROM favorite_movie_table")
    override fun getAllSavedMovies(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT EXISTS (SELECT * FROM favorite_movie_table WHERE id = :movieId)")
    override fun checkIsMovieSaved(movieId: Int): Flow<Boolean>
}