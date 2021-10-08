package com.adapthink.flicktestapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsResponse(

    @field:SerializedName("totalResults")
    val totalResults: Int? = 0,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>? = arrayListOf(),

    @field:SerializedName("status")
    val status: String? = ""
) : Serializable

data class ArticlesItem(

    @field:SerializedName("publishedAt")
    val publishedAt: String? = "",

    @field:SerializedName("author")
    val author: String? = "",

    @field:SerializedName("urlToImage")
    val urlToImage: String? = "",

    @field:SerializedName("description")
    val description: String? = "",

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("url")
    val url: String? = "",

    @field:SerializedName("content")
    val content: String? = ""
) : Serializable

