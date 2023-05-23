package com.sajjadio.trailers.data.repository.genres

import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.genre.Genre
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.utils.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    fun getGenreList(genreId: String): Flow<PagingData<MovieResult>>
    suspend fun getGenresMovie(): Flow<NetworkStatus<Genre?>>
}