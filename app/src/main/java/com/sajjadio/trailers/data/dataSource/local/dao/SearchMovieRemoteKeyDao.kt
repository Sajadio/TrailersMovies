package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.data.base.SearchMovieLocalDataSource
import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey

@Dao
interface SearchMovieRemoteKeyDao : SearchMovieLocalDataSource {

    @Query("SELECT * FROM remote_keys WHERE id =:id")
    override suspend fun getRemoteKeys(id: Int): SearchRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun addAllRemoteKeys(searchRemoteKeys: List<SearchRemoteKey>)

    @Query("DELETE FROM remote_keys")
    override suspend fun deleteAllRemoteKeys()
}