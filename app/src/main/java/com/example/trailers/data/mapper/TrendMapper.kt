package com.example.trailers.data.mapper

import com.example.trailers.data.loacal.trend.TrendResultEntity
import com.example.trailers.data.loacal.trend.TrendEntity
import com.example.trailers.data.model.trend.Trending

class TrendMapper : NetworkMapper<Trending?, List<TrendResultEntity>?> {

    override fun mapFrom(network: Trending?): List<TrendResultEntity>? {
        val list = mutableListOf<TrendResultEntity>()
        network?.results?.forEach { result ->
            list.add(
                TrendResultEntity(
                    id = result.id,
                    poster = result.poster_path,
                )
            )
        }
        return TrendEntity(
            result = list
        ).result
    }
}