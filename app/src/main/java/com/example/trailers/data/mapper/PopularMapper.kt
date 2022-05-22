package com.example.trailers.data.mapper

import com.example.trailers.data.loacal.popular.PopularEntity
import com.example.trailers.data.loacal.popular.PopularResultEntity
import com.example.trailers.data.model.movie.popular.PopularMovie

class PopularMapper : NetworkMapper<PopularMovie?, List<PopularResultEntity>?> {

    override fun mapFrom(network: PopularMovie?): List<PopularResultEntity>? {
        val list = mutableListOf<PopularResultEntity>()
        network?.results?.forEach { result ->
            list.add(
                PopularResultEntity(
                    id = result.id,
                    language = result.original_language,
                    title = result.title,
                    poster = result.poster_path,
                    average = result.vote_average,
                    view = result.vote_count,
                    date = result.release_date,
                )
            )
        }
        return PopularEntity(
            popularResult = list
        ).popularResult
    }
}

