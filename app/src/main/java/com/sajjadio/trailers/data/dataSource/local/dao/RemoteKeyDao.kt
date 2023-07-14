package com.sajjadio.trailers.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sajjadio.trailers.data.dataSource.local.entites.SearchRemoteKey

@Dao
interface RemoteKeyDao {

  @Query("SELECT * FROM remote_keys WHERE id =:id")
  suspend fun getRemoteKeys(id: Int): SearchRemoteKey

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addAllRemoteKeys(searchRemoteKeys: List<SearchRemoteKey>)

  @Query("DELETE FROM remote_keys")
  suspend fun deleteAllRemoteKeys()
}