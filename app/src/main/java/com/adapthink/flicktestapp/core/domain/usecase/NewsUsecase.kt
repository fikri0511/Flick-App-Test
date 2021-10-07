package com.adapthink.flicktestapp.core.domain.usecase

import com.adapthink.flicktestapp.core.data.Resource
import com.adapthink.flicktestapp.core.domain.model.News
import io.reactivex.Flowable

interface NewsUsecase {
    fun getAllNews(): Flowable<Resource<List<News>>>
}