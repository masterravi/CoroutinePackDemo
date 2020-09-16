package com.training.coroutinepackdemo.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class NewsResponse(

    @SerializedName("articles")
    @Expose
    val newsList: List<News>,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int
)
