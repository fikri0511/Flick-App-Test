package com.adapthink.flicktestapp.core.util

import com.adapthink.flicktestapp.core.data.source.local.entity.NewsEntity
import com.adapthink.flicktestapp.core.data.source.remote.response.ArticlesItem
import com.adapthink.flicktestapp.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<ArticlesItem>): List<NewsEntity> {
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                title = it.title!!,
                description = it.description!!,
                publishedAt = it.publishedAt!!,
//                author = it.author!!,
                urlToImage = it.urlToImage!!,
//                source = it.source!!,
                url = it.url!!
//                , content = it.content!!
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                title = it.title!!,
                description = it.description!!,
                publishedAt = it.publishedAt!!,
                author = it.author!!,
                urlToImage = it.urlToImage!!,
//                source = it.source!!,
                url = it.url!!,
                content = it.content!!
            )
        }

    fun mapDomainToEntity(it: News) = NewsEntity(
        title = it.title!!,
        description = it.description!!,
        publishedAt = it.publishedAt!!,
        author = it.author!!,
        urlToImage = it.urlToImage!!,
//        source = it.source!!,
        url = it.url!!,
        content = it.content!!
    )
}