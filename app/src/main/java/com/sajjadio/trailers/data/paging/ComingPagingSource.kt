package com.sajjadio.trailers.data.paging


import com.sajjadio.trailers.data.base.BasePagingSource
import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonDto
import com.sajjadio.trailers.domain.mapper.mapDtoToCommonResultMovieDomain
import com.sajjadio.trailers.domain.model.CommonResult

class ComingPagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : BasePagingSource<Int, CommonDto, CommonResult>() {

    override suspend fun apiCall(pageNumber: Int): CommonDto {
        return movieRemoteDataSource.getUpComingMovie(page = pageNumber)
    }

    override fun mapperResponse(response: CommonDto?): List<CommonResult>? {
        return response?.results?.let {
            mapDtoToCommonResultMovieDomain(it)
        }
    }
}