package com.comp491.investsmart.domain.assets.entities

data class Asset(
    val assetName: String,
    val assetTicker: String,
    val lastPrice: Double,
    val assetCategoryName: String,
    val viewCount: Int,
    val imageUrl: String,
    val marketSize: Double,
    val followerCount: Int,
)
