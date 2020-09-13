package com.training.coroutinepackdemo.data.api

import com.training.coroutinepackdemo.data.local.entity.NewsEntity

class  ApiHelperImpl(private val apiService: ApiService):ApiHelper{

    override suspend fun getNews(pageId:String):List<NewsEntity> = apiService.getNews(pageId)

    override suspend fun getMoreNews():List<NewsEntity> = apiService.moreNews()
}