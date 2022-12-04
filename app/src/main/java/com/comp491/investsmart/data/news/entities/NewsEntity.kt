package com.comp491.investsmart.data.news.entities

import androidx.annotation.Keep
import com.comp491.investsmart.domain.news.entities.News
import com.squareup.moshi.Json

@Keep
data class NewsEntity(
    @Json(name = "title")
    val title: String,

    @Json(name = "description")
    val description: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "published_date")
    val publishedDate: String,

    @Json(name = "publisher")
    val publisher: String,

    @Json(name = "asset_ticker")
    val assetTicker: String,

    @Json(name = "summary")
    val summary: String,
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

fun NewsEntity.toDomain(): News {
    return News(
        title = title,
        description = description,
        url = url,
        publishedDate = publishedDate,
        publisher = publisher,
        assetTicker = assetTicker,
        summary = summary,
    )
}
