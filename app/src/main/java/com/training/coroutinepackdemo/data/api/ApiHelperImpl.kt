package com.training.coroutinepackdemo.data.api

import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.data.models.NewsResponse
import com.training.coroutinepackdemo.utils.Constants

class  ApiHelperImpl(private val apiService: ApiService):ApiHelper{

    override suspend fun getNews(pageId:String):NewsResponse = apiService.getNews(pageId,Constants.API_KEY,Constants.country)

    override suspend fun getMoreNews():List<NewsEntity> = apiService.moreNews()
}
