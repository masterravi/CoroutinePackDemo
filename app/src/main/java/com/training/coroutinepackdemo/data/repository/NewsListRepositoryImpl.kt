package com.training.coroutinepackdemo.data.repository

import com.training.coroutinepackdemo.data.api.ApiHelper
import com.training.coroutinepackdemo.data.local.dao.NewsDaoImpl
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.data.models.News
import com.training.coroutinepackdemo.utils.NetworkHelper

class NewsListRepositoryImpl(private val apiHelper: ApiHelper, private val networkHelper: NetworkHelper, private val databaseService: NewsDaoImpl):NewsListRepository{

    override suspend fun getNews(pageId:String):List<NewsEntity>{
        val newsList= mutableListOf<NewsEntity>()
        if(networkHelper.isNetworkConnected()){
            var newsResponse=  apiHelper.getNews(pageId)
            if(newsResponse.totalResults>0){
                newsResponse.newsList?.forEach {
                    val newsEntity= NewsEntity(
                        id = 0,
                        title = it.title,
                        description = it.description,
                        urlToImage = it.urlToImage,
                        source = it.source.name,
                        url = it.url
                    )
                    newsList.add(newsEntity)
                }
            }
            return  newsList
        }else{
            return  databaseService.getAll()
        }

    }

    override suspend fun getMoreNews():List<NewsEntity> = apiHelper.getMoreNews()

}
