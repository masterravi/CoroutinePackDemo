package com.training.coroutinepackdemo.data.api

import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.data.models.NewsResponse

interface  ApiHelper{

    suspend fun getNews(pageId:String): NewsResponse

    suspend fun getMoreNews():List<NewsEntity>
}
