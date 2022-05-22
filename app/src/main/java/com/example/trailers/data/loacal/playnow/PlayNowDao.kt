package com.example.trailers.data.loacal.playnow

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayNowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<PlayNowResultEntity>)

    @Query("SELECT * FROM tb_play_now ")
    fun fetchData(): Flow<List<PlayNowResultEntity>?>

    @Query("DELETE FROM tb_play_now")
    suspend fun deleteAllItem()

}