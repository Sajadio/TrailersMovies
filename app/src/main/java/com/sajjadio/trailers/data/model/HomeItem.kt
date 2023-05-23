package com.sajjadio.trailers.data.model

import com.sajjadio.trailers.data.model.movie.common.CommonResult
import com.sajjadio.trailers.data.model.movie.trend.TrendResult

sealed class HomeItem(val priority: Int,val item:Any) {
    class Trend(val trend: List<TrendResult>) : HomeItem(0,trend)
    class Popular(val popular: List<CommonResult>) : HomeItem(1,popular)
    class TopRated(val topRated: List<CommonResult>) : HomeItem(2,topRated)
    class Upcoming(val upComing: List<CommonResult>) : HomeItem(3,upComing)
}
