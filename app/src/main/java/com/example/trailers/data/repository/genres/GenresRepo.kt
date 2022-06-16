package com.example.trailers.data.repository.genres

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trailers.data.model.movie.genremovie.MovieResult
import com.example.trailers.data.network.ApiService
import com.example.trailers.data.repository.common.GenresPagingSource
import com.example.trailers.utils.Constant
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenresRepo @Inject constructor(
    private val api: ApiService,
) : SafeApiCall {

    fun getGenreList(genreId: String): Flow<PagingData<MovieResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                GenresPagingSource(genreId = genreId, api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    suspend fun getGenresMovie() = safeApiCall(api.getGenresMovie()).flowOn(Dispatchers.IO)
}