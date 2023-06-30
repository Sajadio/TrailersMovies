package com.sajjadio.trailers.ui.home.utils

import com.sajjadio.trailers.domain.model.CommonResult
import com.sajjadio.trailers.domain.model.TrendMovieResult

sealed class HomeItem(val rank: Int, val item:Any) {
    class Trend(val trend: List<TrendMovieResult>) : HomeItem(0,trend)
    class Popular(val popular: List<CommonResult>) : HomeItem(1,popular)
    class TopRated(val topRated: List<CommonResult>) : HomeItem(2,topRated)
    class Upcoming(val upComing: List<CommonResult>) : HomeItem(3,upComing)
}
