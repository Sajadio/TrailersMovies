package com.example.trailers.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.example.trailers.data.loacal.TrailersDatabase
import com.example.trailers.data.mapper.*
import com.example.trailers.data.network.ApiService
import com.example.trailers.utils.SafeApiCall
import com.example.trailers.utils.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepo @Inject constructor(
    private val api: ApiService,
    private val db: TrailersDatabase,
) : SafeApiCall {

//    fun getMoviePopular() = safeApiCall {
//        PopularMapper().mapFrom(
//            api.getMoviePopular()
//        )
//    }.flowOn(Dispatchers.IO)

//    fun getMovieTopRated() = safeApiCall {
//        RatedMapper().mapFrom(
//            api.getMovieTopRated()
//        )
//    }.flowOn(Dispatchers.IO)
//
//    fun getUpComingMovie() = safeApiCall {
//        ComingMapper().mapFrom(
//            api.getUpComingMovie()
//        )
//    }.flowOn(Dispatchers.IO)


    fun getMoviePopular() = networkBoundResource(
        query = {
            db.getPopularDao().fetchData().distinctUntilChanged()
        },
        fetch = {
            PopularMapper().mapFrom(
                api.getMoviePopular()
            )
        },
        saveFetchResult = {
            db.withTransaction {
                it?.let {
                    db.getPopularDao().deleteAllItem()
                    db.getPopularDao().insert(it)
                }
            }
        }
    )

    fun getMovieTopRated() = networkBoundResource(
        query = {
            db.getRatedDao().fetchData().distinctUntilChanged()
        },
        fetch = {
            RatedMapper().mapFrom(
                api.getMovieTopRated()
            )
        },
        saveFetchResult = {
            db.withTransaction {
                it?.let {
                    db.getRatedDao().deleteAllItem()
                    db.getRatedDao().insert(it)
                }
            }
        }
    )

    fun getUpComingMovie() = networkBoundResource(
        query = {
            db.getComingDao().fetchData().distinctUntilChanged()
        },
        fetch = {
            ComingMapper().mapFrom(
                api.getUpComingMovie()
            )
        },
        saveFetchResult = {
            db.withTransaction {
                it?.let {
                    db.getComingDao().deleteAllItem()
                    db.getComingDao().insert(it)
                }
            }
        }
    )

    fun getMoviePlayNow() = networkBoundResource(
        query = {
            db.getPlayNowDao().fetchData().distinctUntilChanged()
        },
        fetch = {
            PlayNowMapper().mapFrom(
                api.getMoviePlayNow()
            )
        },
        saveFetchResult = {
            db.withTransaction {
                it?.let {
                    db.getPlayNowDao().deleteAllItem()
                    db.getPlayNowDao().insert(it)
                }
            }
        }
    )

}