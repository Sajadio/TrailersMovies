package com.example.trailers.data.mapper

import com.example.trailers.data.loacal.coming.ComingEntity
import com.example.trailers.data.loacal.coming.ComingResultEntity
import com.example.trailers.data.model.movie.upcoming.UPComingMovie

class ComingMapper : NetworkMapper<UPComingMovie?, List<ComingResultEntity>?> {

    override fun mapFrom(network: UPComingMovie?): List<ComingResultEntity>? {
        val list = mutableListOf<ComingResultEntity>()
        network?.results?.forEach { result ->
            list.add(
                ComingResultEntity(
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
        return ComingEntity(
            comingResult = list
        ).comingResult
    }
}