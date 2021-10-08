package com.adapthink.flicktestapp.core.domain.repository

import com.adapthink.flicktestapp.core.data.Resource
import com.adapthink.flicktestapp.core.domain.model.News
import io.reactivex.Flowable

interface INewsRepository {
    fun getAllNews(): Flowable<Resource<List<News>>>
}