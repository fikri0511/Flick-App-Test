package com.adapthink.flicktestapp.core.data

import com.adapthink.flicktestapp.core.data.source.local.LocalDataSource
import com.adapthink.flicktestapp.core.data.source.remote.RemoteDataSource
import com.adapthink.flicktestapp.core.data.source.remote.network.ApiResponse
import com.adapthink.flicktestapp.core.data.source.remote.response.ArticlesItem
import com.adapthink.flicktestapp.core.data.source.remote.response.NewsResponse
import com.adapthink.flicktestapp.core.domain.model.News
import com.adapthink.flicktestapp.core.domain.repository.INewsRepository
import com.adapthink.flicktestapp.core.util.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : INewsRepository {

    override fun getAllNews(): Flowable<Resource<List<News>>> =
        object : NetworkBoundResource<List<News>, List<NewsResponse>>() {
            override fun loadFromDB(): Flowable<List<News>> {
                return localDataSource.getAllNews()
                    .map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<News>?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<List<ArticlesItem>>> =
                remoteDataSource.getAllNews()

            override fun saveCallResult(data: List<ArticlesItem>) {
                val newsList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertNews(newsList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
}
