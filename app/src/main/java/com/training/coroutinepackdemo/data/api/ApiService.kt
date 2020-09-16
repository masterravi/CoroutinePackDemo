package com.training.coroutinepackdemo.data.api

import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import com.training.coroutinepackdemo.data.models.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNews(@Query("page") pageId: String, @Query("apiKey") apiKey: String, @Query("country") country: String):NewsResponse

    @GET("more-newsList")
    suspend fun moreNews():List<NewsEntity>
}
