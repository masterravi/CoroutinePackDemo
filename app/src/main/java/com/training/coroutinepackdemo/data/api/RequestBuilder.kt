package com.training.coroutinepackdemo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestBuilder{

    private const val BASE_URL = "https://demo1985203.mockable.io/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)

}