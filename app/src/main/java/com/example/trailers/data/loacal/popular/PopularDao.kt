package com.example.trailers.data.loacal.popular

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<PopularResultEntity>)

    @Query("SELECT * FROM tb_popular")
    fun fetchData(): Flow<List<PopularResultEntity>>

    @Query("DELETE FROM tb_popular")
    suspend fun deleteAllItem()

}