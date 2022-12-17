package com.comp491.investsmart.data.assets.entities

import androidx.annotation.Keep
import com.comp491.investsmart.domain.assets.entities.AssetPrice
import com.squareup.moshi.Json

@Keep
data class AssetPriceEntity(
    @Json(name = "asset_name")
    val assetName: String,

    @Json(name = "asset_ticker")
    val assetTicker: String,

    @Json(name = "date_time")
    val date: String,

    @Json(name = "price")
    val price: Double,

    @Json(name = "volume")
    val volume: Double,
)

fun AssetPriceEntity.toDomain(): AssetPrice {
    return AssetPrice(
        assetName = assetName,
        assetTicker = assetTicker,
        date = date,
        price = price,
        volume = volume,
    )
}
