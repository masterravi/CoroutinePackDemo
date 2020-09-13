package com.training.coroutinepackdemo.data.local.dao

import com.training.coroutinepackdemo.data.local.AppDatabase
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.jetdemo.data.local.db.dao.NewsDao

class NewsDaoImpl(private val appDatabase: AppDatabase) : NewsDao {

    override suspend fun getAll(): List<NewsEntity> = appDatabase.newsDao().getAll()

    override suspend fun insertAll(users: List<NewsEntity>) = appDatabase.newsDao().insertAll(users)

}