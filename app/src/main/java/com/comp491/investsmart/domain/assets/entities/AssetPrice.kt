package com.comp491.investsmart.domain.assets.entities

import java.util.*

data class AssetPrice(
    val assetName: String,
    val assetTicker: String,
    val date: Date,
    val price: Double,
    val volume: Double,
)
