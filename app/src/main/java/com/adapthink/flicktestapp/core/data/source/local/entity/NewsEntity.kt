package com.adapthink.flicktestapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,

    @ColumnInfo(name = "author")
    var author: String="",

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String,

    @ColumnInfo(name = "description")
    var description: String,

//    @ColumnInfo(name = "source")
//    var source: Source,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "content")
    var content: String =""
)

//data class Source(
//    @ColumnInfo(name = "name")
//    val name: String? = null,
//
//    @ColumnInfo(name = "id")
//    val id: String? = null
//) : Serializable