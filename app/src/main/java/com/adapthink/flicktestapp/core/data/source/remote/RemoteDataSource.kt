package com.adapthink.flicktestapp.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.adapthink.flicktestapp.core.data.source.remote.network.ApiResponse
import com.adapthink.flicktestapp.core.data.source.remote.network.ApiService
import com.adapthink.flicktestapp.core.data.source.remote.response.ArticlesItem
import com.adapthink.flicktestapp.core.util.ConstGlobal.API_KEY
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    @SuppressLint("CheckResult")
    fun getAllNews(): Flowable<ApiResponse<List<ArticlesItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ArticlesItem>>>()

        //get data from remote api
        val client = apiService.getListNews("id", "health", API_KEY)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.articles
                resultData.onNext((if (dataArray!!.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty))
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}