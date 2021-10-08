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

class RemoteDataSource(private val apiService: ApiService) {
    @SuppressLint("CheckResult")
    fun getAllNews(): Flowable<ApiResponse<List<ArticlesItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<ArticlesItem>>>()
        //get data news
        val client = apiService.getListNews("id", "sports", API_KEY)

        client
            .subscribeOn(Schedulers.io())
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