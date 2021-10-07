package com.adapthink.flicktestapp.core.domain.usecase

import com.adapthink.flicktestapp.core.domain.repository.INewsRepository

class NewsInteractor(private val iNewsRepository: INewsRepository) : NewsUsecase {
    override fun getAllNews() = iNewsRepository.getAllNews()
}