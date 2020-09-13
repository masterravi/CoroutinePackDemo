package com.training.coroutinepackdemo.data.api

import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("news/{pageId}")
    suspend fun getNews(@Path("pageId") pageId: String):List<NewsEntity>

    @GET("more-news")
    suspend fun moreNews():List<NewsEntity>
}