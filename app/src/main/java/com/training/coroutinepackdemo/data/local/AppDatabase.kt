package com.training.coroutinepackdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.training.jetdemo.data.local.db.dao.NewsDao
import com.training.coroutinepackdemo.data.local.entity.NewsEntity

@Database(
    entities = [
        NewsEntity::class
    ],
    exportSchema = false,
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}