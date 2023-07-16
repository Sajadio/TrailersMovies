package com.sajjadio.trailers.data.paging

import com.sajjadio.trailers.data.base.BasePagingSource
import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.dataSource.mapper.mapSearchMovieEntity
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonDto
import com.sajjadio.trailers.data.dataSource.model.movie.search.SearchMovieDto
import com.sajjadio.trailers.domain.mapper.mapDtoToCommonResultMovieDomain
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.SearchMovieResult

class SearchPagingData(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val query: String?,
    private val lang: String
) : BasePagingSource<Int, SearchMovieDto, SearchMovieResult>() {

    override suspend fun apiCall(pageNumber: Int): SearchMovieDto {
        return movieRemoteDataSource.getSearchMovieByQuery(
            page = pageNumber,
            query = query,
            lang = lang
        )
    }

    override fun mapperResponse(response: SearchMovieDto?): List<SearchMovieResult>? {
        return response?.results?.let {
            mapSearchMovieEntity(it)
        }
    }
}