package com.adapthink.flicktestapp.di

import com.adapthink.flicktestapp.core.domain.usecase.NewsInteractor
import com.adapthink.flicktestapp.core.domain.usecase.NewsUsecase
import com.adapthink.flicktestapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val useCaseModule = module {
    factory<NewsUsecase> { NewsInteractor(get()) }
}
