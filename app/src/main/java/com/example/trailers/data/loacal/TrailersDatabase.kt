package com.example.trailers.data.loacal

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trailers.data.loacal.coming.ComingDao
import com.example.trailers.data.loacal.coming.ComingResultEntity
import com.example.trailers.data.loacal.popular.PopularDao
import com.example.trailers.data.loacal.popular.PopularResultEntity
import com.example.trailers.data.loacal.rated.RatedDao
import com.example.trailers.data.loacal.rated.RatedResultEntity
import com.example.trailers.data.loacal.trend.TrendResultEntity
import com.example.trailers.data.loacal.trend.TrendDao
import javax.inject.Singleton

@Database(entities = [
    PopularResultEntity::class,
    TrendResultEntity::class,
    RatedResultEntity::class,
    ComingResultEntity::class
],
    version = 1,
    exportSchema = false)
//@TypeConverters(DataConverter::class)
@Singleton
abstract class TrailersDatabase : RoomDatabase() {
    abstract fun getPopularDao(): PopularDao
    abstract fun getTrendDao(): TrendDao
    abstract fun getRatedDao(): RatedDao
    abstract fun getComingDao(): ComingDao
}