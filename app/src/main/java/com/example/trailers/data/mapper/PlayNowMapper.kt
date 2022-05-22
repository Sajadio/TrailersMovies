package com.example.trailers.data.mapper

import com.example.trailers.data.loacal.playnow.PlayNowEntity
import com.example.trailers.data.loacal.playnow.PlayNowResultEntity
import com.example.trailers.data.model.movie.playnow.MoviePlayNow

class PlayNowMapper : NetworkMapper<MoviePlayNow?, List<PlayNowResultEntity>?> {

    override fun mapFrom(network: MoviePlayNow?): List<PlayNowResultEntity>? {
        val list = mutableListOf<PlayNowResultEntity>()
        network?.results?.forEach { result ->
            list.add(
                PlayNowResultEntity(
                    id = result.id,
                    poster = result.poster_path,
                )
            )
        }
        return PlayNowEntity(
            result = list
        ).result
    }
}