package com.adapthink.flicktestapp.core.di

import android.content.Context
import com.adapthink.flicktestapp.core.data.NewsRepository
import com.adapthink.flicktestapp.core.data.source.local.LocalDataSource
import com.adapthink.flicktestapp.core.data.source.local.room.NewsDatabase
import com.adapthink.flicktestapp.core.data.source.remote.RemoteDataSource
import com.adapthink.flicktestapp.core.data.source.remote.network.ApiConfig
import com.adapthink.flicktestapp.core.domain.repository.INewsRepository
import com.adapthink.flicktestapp.core.domain.usecase.NewsInteractor
import com.adapthink.flicktestapp.core.domain.usecase.NewsUsecase
import com.adapthink.flicktestapp.core.util.AppExecutors

object Injection {
    private fun provideRepository(context: Context): INewsRepository {
        val database = NewsDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.newsDao())
        val appExecutors = AppExecutors()

        return NewsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideNewsUseCase(context: Context): NewsUsecase {
        val repository = provideRepository(context)
        return NewsInteractor(repository)
    }
}
