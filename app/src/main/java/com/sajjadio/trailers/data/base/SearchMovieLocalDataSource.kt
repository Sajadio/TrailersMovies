package com.sajjadio.trailers.data.base

import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey

interface SearchMovieLocalDataSource {
    suspend fun getRemoteKeys(id: Int): SearchRemoteKey
    suspend fun addAllRemoteKeys(searchRemoteKeys: List<SearchRemoteKey>)
    suspend fun deleteAllRemoteKeys()
}