package com.sajjadio.trailers.domain.mapper


import com.sajjadio.trailers.data.dataSource.local.entites.PopularMovieEntity
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto
import com.sajjadio.trailers.domain.model.CommonResult


internal fun mapToPopularMovieDomain(input:PopularMovieEntity): CommonResult {
    return  CommonResult(
            id = input.id,
            original_title = input.original_title,
            poster_path = input.poster_path,
            vote_average = input.vote_average,
            release_date = input.release_date,
        )
}



