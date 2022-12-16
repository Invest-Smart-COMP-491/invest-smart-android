package com.comp491.investsmart.data.news.entities

import androidx.annotation.Keep
import com.comp491.investsmart.data.utils.getFormattedDateString
import com.comp491.investsmart.domain.news.entities.News
import com.squareup.moshi.Json
@Keep
data class NewsEntity(
    @Json(name = "asset_ticker")
    val assetTicker: String,

    @Json(name = "title")
    val title: String,

    @Json(name = "url")
    val url: String,

    @Json(name = "published_date")
    val publishedDate: String,

    @Json(name = "publisher")
    val publisher: String,

    @Json(name = "summary")
    val summary: String,

    @Json(name = "thumbnail")
    val thumbnail: String?,

    @Json(name = "mentioned_asset")
    val mentionedAsset: List<String>?,

    @Json(name = "asset")
    val asset: Int,

    @Json(name = "id")
    val id: Int,
)

fun NewsEntity.toDomain(): News {
    return News(
        title = title,
        url = url,
        publishedDate = getFormattedDateString(publishedDate),
        publisher = publisher,
        assetTicker = assetTicker,
        summary = summary,
        thumbnail = thumbnail,
    )
}
