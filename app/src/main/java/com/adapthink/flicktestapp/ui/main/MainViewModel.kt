package com.adapthink.flicktestapp.ui.main

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.adapthink.flicktestapp.core.domain.usecase.NewsUsecase

class MainViewModel(newsUsecase: NewsUsecase) : ViewModel() {
    val news = LiveDataReactiveStreams.fromPublisher(newsUsecase.getAllNews())
}