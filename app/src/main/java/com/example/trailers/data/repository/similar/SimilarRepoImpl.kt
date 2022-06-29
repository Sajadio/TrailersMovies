package com.example.trailers.data.repository.similar

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.trailers.data.model.movie.similar.Result
import com.example.trailers.data.network.ApiService
import com.example.trailers.data.repository.movie.MoviePagingData
import com.example.trailers.utils.Constant
import com.example.trailers.utils.SafeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SimilarRepoImpl @Inject constructor(
    private val api: ApiService,
) : SimilarRepository, SafeApiCall {

    override fun listSimilarOfMovie(id: Int): Flow<PagingData<Result>> =
        Pager(config = PagingConfig(pageSize = Constant.DEFAULT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { MoviePagingData(api = api, id = id) }
        ).flow.flowOn(Dispatchers.IO)
}