package com.sajjadio.trailers.domain.mapper


import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto
import com.sajjadio.trailers.domain.model.CommonResult

internal fun mapDtoToCommonResultMovieDomain(input:List<CommonResultDto>): List<CommonResult> {
    return input.map {
        CommonResult(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
        )
    }
}



