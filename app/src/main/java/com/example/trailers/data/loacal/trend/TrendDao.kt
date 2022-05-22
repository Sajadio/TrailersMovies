package com.example.trailers.data.loacal.trend

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TrendDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<TrendResultEntity>)

    @Query("SELECT * FROM tb_trend")
    fun fetchData(): Flow<List<TrendResultEntity>>

    @Query("DELETE FROM tb_trend")
    suspend fun deleteAllItem()

}