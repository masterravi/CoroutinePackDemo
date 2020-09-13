package com.training.jetdemo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.training.coroutinepackdemo.data.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_entity")
    suspend fun getAll(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(item: List<NewsEntity>)
}