package com.comp491.investsmart.data.assets.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class AssetTickerEntity (
    @Json(name = "asset")
    val assetTicker: String,
)
