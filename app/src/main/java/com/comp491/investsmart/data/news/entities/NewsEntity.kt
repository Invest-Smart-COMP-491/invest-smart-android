package com.comp491.investsmart.data.news.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json
import java.util.*

@Keep
data class NewsEntity(
    @Json(name = "title")
    val title: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "published_date")
    val publishedDate: Date,

    @Json(name = "publisher")
    val publisher: String,

    @Json(name = "asset_ticker")
    val assetTicker: String,
)

@Keep
data class Post(
    @Json(name = "id")
    val id_: Int = 0,
    @Json(name = "title")
    val title: String = "",
    @Json(name = "body")
    val body: String = "",
    @Json(name = "userId")
    val userId: Int = 0
)
