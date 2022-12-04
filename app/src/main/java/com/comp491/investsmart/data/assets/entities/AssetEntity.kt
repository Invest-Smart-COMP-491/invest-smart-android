package com.comp491.investsmart.data.assets.entities

import androidx.annotation.Keep
import com.comp491.investsmart.domain.assets.entities.Asset
import com.squareup.moshi.Json

@Keep
data class AssetEntity(
    @Json(name = "asset_name")
    val assetName: String,

    @Json(name = "asset_ticker")
    val assetTicker: String,

    @Json(name = "last_price")
    val lastPrice: Double,

    @Json(name = "asset_category_name")
    val assetCategory: String,

    @Json(name = "view_count")
    val viewCount: Int,

    @Json(name = "photo_link")
    val photoUrl: String,

    @Json(name = "market_size")
    val marketSize: Double,
)

fun AssetEntity.toDomain(): Asset {
    return Asset(
        assetName = assetName,
        assetTicker = assetTicker,
        lastPrice = lastPrice,
        assetCategory = assetCategory,
        viewCount = viewCount,
        photoUrl = photoUrl,
        marketSize = marketSize,
    )
}
