package com.adapthink.flicktestapp.core.data.source.local

import com.adapthink.flicktestapp.core.data.source.local.entity.NewsEntity
import com.adapthink.flicktestapp.core.data.source.local.room.NewsDao
import io.reactivex.Flowable

class LocalDataSource private constructor(private val newsDao: NewsDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(newsDao: NewsDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(newsDao)
            }
    }

    fun getAllNews(): Flowable<List<NewsEntity>> = newsDao.getAllNews()
    fun insertNews(news: List<NewsEntity>) =
        newsDao.insertNews(news)
}
