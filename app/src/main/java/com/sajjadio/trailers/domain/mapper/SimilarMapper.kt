package com.sajjadio.trailers.domain.mapper

import com.sajjadio.trailers.data.model.movie.similar.SimilarResultDto
import com.sajjadio.trailers.domain.model.SimilarResult
import javax.inject.Inject

class SimilarMapper @Inject constructor() : Mapper<List<SimilarResultDto>, List<SimilarResult>> {
    override fun mapTo(input: List<SimilarResultDto>): List<SimilarResult> {
        return input.map {
            SimilarResult(
                id = it.id,
                original_title = it.original_title,
                poster_path = it.poster_path,
                vote_average = it.vote_average,
                vote_count = it.vote_count,
            )
        }
    }
}