package com.example.trailers.data.mapper

import com.example.trailers.data.loacal.rated.RatedEntity
import com.example.trailers.data.loacal.rated.RatedResultEntity
import com.example.trailers.data.model.movie.rate.TopRatedMovie

class RatedMapper : NetworkMapper<TopRatedMovie?, List<RatedResultEntity>?> {

    override fun mapFrom(network: TopRatedMovie?): List<RatedResultEntity>? {
        val list = mutableListOf<RatedResultEntity>()
        network?.results?.forEach { result ->
            list.add(
                RatedResultEntity(
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
        return RatedEntity(
            ratedResult = list
        ).ratedResult
    }
}