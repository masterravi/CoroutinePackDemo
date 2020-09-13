package com.training.coroutinepackdemo.data.api

import com.training.coroutinepackdemo.data.local.entity.NewsEntity

interface  ApiHelper{

    suspend fun getNews(pageId:String):List<NewsEntity>

    suspend fun getMoreNews():List<NewsEntity>
}