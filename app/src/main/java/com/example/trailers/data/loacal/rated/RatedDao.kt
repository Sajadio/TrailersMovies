package com.example.trailers.data.loacal.rated

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RatedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<RatedResultEntity>)

    @Query("SELECT * FROM tb_rated ORDER BY id DESC")
    fun fetchData(): Flow<List<RatedResultEntity>>

    @Query("DELETE FROM tb_rated")
    suspend fun deleteAllItem()

}