package com.adapthink.flicktestapp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adapthink.flicktestapp.core.data.source.local.entity.NewsEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAllNews(): Flowable<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: List<NewsEntity>): Completable

}
