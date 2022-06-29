package com.example.trailers.data.repository.genres

import androidx.paging.PagingData
import com.example.trailers.data.model.genre.Genre
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    fun getGenreList(genreId: String): Flow<PagingData<MovieResult>>
    suspend fun getGenresMovie(): Flow<NetworkStatus<Genre>>
}