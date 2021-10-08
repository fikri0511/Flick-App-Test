package com.adapthink.flicktestapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(

    val publishedAt: String,
    val author: String,
    val urlToImage: String="",
    val description: String,
    val title: String,
    val url: String,
    val content: String
) : Parcelable
