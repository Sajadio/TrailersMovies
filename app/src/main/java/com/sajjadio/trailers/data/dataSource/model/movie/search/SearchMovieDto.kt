package com.sajjadio.trailers.data.dataSource.model.movie.search

import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonResultDto

data class SearchMovieDto(
    val page: Int?,
    val results: List<CommonResultDto>?,
    val total_pages: Int?,
    val total_results: Int?
)