package com.comp491.investsmart.domain.assets.entities

data class AssetPrice(
    val assetName: String,
    val assetTicker: String,
    val date: String,
    val price: Double,
    val volume: Double,
)
