package com.sajjadio.trailers.domain.mapper


import com.sajjadio.trailers.data.dataSource.local.entites.SearchMovieEntity
import com.sajjadio.trailers.data.dataSource.local.entites.UpcomingMovieEntity
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.SearchResult


internal fun mapToSearchMovieDomain(input: List<SearchMovieEntity>): List<SearchResult> {
    return input.map{
        SearchResult(
            id = it.id,
            original_title = it.original_title,
            poster_path = it.poster_path,
            vote_average = it.vote_average,
            release_date = it.release_date,
            vote_count = it.vote_count
        )
    }
}

