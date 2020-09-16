package com.training.coroutinepackdemo.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "news_entity")
data class NewsEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String?=null,

    @ColumnInfo(name = "description")
    val description: String?=null,

    @ColumnInfo(name = "urlToImage")
    val urlToImage: String?=null,

    @ColumnInfo(name = "source")
    val source: String?=null,

    @ColumnInfo(name = "url")
    val url: String?=null


):Serializable
