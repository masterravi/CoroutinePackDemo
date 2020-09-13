package com.training.coroutinepackdemo.data.repository

import com.training.coroutinepackdemo.data.local.entity.NewsEntity


interface NewsListRepository {

    suspend fun getNews(pageId:String):List<NewsEntity>

    suspend fun getMoreNews():List<NewsEntity>
}