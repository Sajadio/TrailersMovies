package com.example.trailers.data.repository.similar

import androidx.paging.PagingData
import com.example.trailers.data.model.movie.similar.Result
import kotlinx.coroutines.flow.Flow

interface SimilarRepository {
    fun listSimilarOfMovie(id: Int): Flow<PagingData<Result>>
}