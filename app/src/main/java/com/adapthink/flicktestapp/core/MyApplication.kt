package com.adapthink.flicktestapp.core

import android.app.Application
import com.adapthink.flicktestapp.core.di.databaseModule
import com.adapthink.flicktestapp.core.di.networkModule
import com.adapthink.flicktestapp.core.di.repositoryModule
import com.adapthink.flicktestapp.di.useCaseModule
import com.adapthink.flicktestapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}