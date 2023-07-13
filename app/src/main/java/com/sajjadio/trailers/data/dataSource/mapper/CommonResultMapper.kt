package com.sajjadio.trailers.data.dataSource.mapper


import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonDto
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto
import com.sajjadio.trailers.domain.model.Common
import com.sajjadio.trailers.domain.model.CommonResult

internal fun mapToCommonDomain(input: CommonDto): Common {
    return Common(
        page = input.page,
        results = mapToCommonResultDomain(input.results),
        total_pages = input.total_pages,
        total_results = input.total_results,
    )
}

internal fun mapToCommonResultDomain(input: List<CommonResultDto>): List<CommonResult> {
    return input.map {
        CommonResult(
            id = it.id,
            original_title = it.original_title,
            overview = it.overview,
            poster_path = it.poster_path,
            vote_average = it.vote_average?.toFloat(),
            vote_count = it.vote_count,
            release_date = it.release_date,
        )
    }
}

