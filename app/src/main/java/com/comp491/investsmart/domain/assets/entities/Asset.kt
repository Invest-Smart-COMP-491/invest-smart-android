package com.comp491.investsmart.domain.assets.entities

data class Asset(
    val assetName: String,
    val assetTicker: String,
    val lastPrice: Double,
    val assetCategory: String,
    val viewCount: Int,
    val photoUrl: String,
    val marketSize: Double,
)
