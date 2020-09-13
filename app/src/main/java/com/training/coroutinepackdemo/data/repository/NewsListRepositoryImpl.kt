package com.training.coroutinepackdemo.data.repository

import com.training.coroutinepackdemo.data.api.ApiHelper
import com.training.coroutinepackdemo.data.local.dao.NewsDaoImpl
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.utils.NetworkHelper

class NewsListRepositoryImpl(private val apiHelper: ApiHelper, private val networkHelper: NetworkHelper, private val databaseService: NewsDaoImpl):NewsListRepository{

    override suspend fun getNews(pageId:String):List<NewsEntity>{
        if(networkHelper.isNetworkConnected())
            return apiHelper.getNews(pageId)
        else
            return databaseService.getAll()
    }

    override suspend fun getMoreNews():List<NewsEntity> = apiHelper.getMoreNews()

}