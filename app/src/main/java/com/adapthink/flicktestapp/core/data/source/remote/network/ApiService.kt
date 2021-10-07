package com.adapthink.flicktestapp.core.data.source.remote.network

import com.adapthink.flicktestapp.core.data.source.remote.response.NewsResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/top-headlines")
    fun getListNews(
        @Query("country") country: String?,
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String?
    ): Flowable<NewsResponse>
}