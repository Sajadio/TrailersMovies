package com.sajjadio.trailers.data.repository.genres

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.genremovie.MovieResult
import com.sajjadio.trailers.data.network.ApiService
import com.sajjadio.trailers.data.repository.common.GenresPagingSource
import com.sajjadio.trailers.utils.Constant
import com.sajjadio.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenresRepoImpl @Inject constructor(
    private val api: ApiService,
) : GenresRepository, SafeApiCall {

    override fun getGenreList(genreId: String): Flow<PagingData<MovieResult>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = {
                GenresPagingSource(genreId = genreId, api = api)
            }
        ).flow.flowOn(Dispatchers.IO)

    override suspend fun getGenresMovie() = safeApiCall{api.getGenresMovie()}.flowOn(Dispatchers.IO)
}