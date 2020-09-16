package com.training.coroutinepackdemo.data.models

data class News(

    val source: Source,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
