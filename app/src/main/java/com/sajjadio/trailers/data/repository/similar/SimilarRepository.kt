package com.sajjadio.trailers.data.repository.similar

import androidx.paging.PagingData
import com.sajjadio.trailers.data.model.movie.similar.Result
import kotlinx.coroutines.flow.Flow

interface SimilarRepository {
    fun listSimilarOfMovie(id: Int): Flow<PagingData<Result>>
}