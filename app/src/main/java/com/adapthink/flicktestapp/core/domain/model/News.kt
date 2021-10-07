package com.adapthink.flicktestapp.core.domain.model

import android.os.Parcelable
import com.adapthink.flicktestapp.core.data.source.remote.response.Source
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(

    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
//    val source: SourceDomain,
    val title: String,
    val url: String,
    val content: String
) : Parcelable

@Parcelize
data class SourceDomain(
    val name: String,
    val id: String
) : Parcelable
