package com.sajjadio.trailers.data.paging


import com.sajjadio.trailers.data.base.BasePagingSource
import com.sajjadio.trailers.data.base.MovieRemoteDataSource
import com.sajjadio.trailers.data.dataSource.model.movie.common.CommonDto
import com.sajjadio.trailers.domain.mapper.mapDtoToCommonResultMovieDomain
import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.utils.language

class PopularPagingSource(
    private val movieRemoteDataSource: MovieRemoteDataSource,
   private val lang: String,
) : BasePagingSource<Int, CommonDto, CommonResult>() {

    override suspend fun apiCall(pageNumber: Int): CommonDto {
        return movieRemoteDataSource.getPopularMovie(page = pageNumber, lang = lang)
    }

    override fun mapperResponse(response: CommonDto?): List<CommonResult>? {
        return response?.results?.let {
            mapDtoToCommonResultMovieDomain(it)
        }
    }
}