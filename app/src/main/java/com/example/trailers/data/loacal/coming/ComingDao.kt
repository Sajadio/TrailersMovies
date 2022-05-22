package com.example.trailers.data.loacal.coming

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ComingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<ComingResultEntity>)

    @Query("SELECT * FROM tb_coming")
    fun fetchData(): Flow<List<ComingResultEntity>>

    @Query("DELETE FROM tb_coming")
    suspend fun deleteAllItem()

}