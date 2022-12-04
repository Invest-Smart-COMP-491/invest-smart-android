package com.comp491.investsmart.domain.news.entities

data class News(
    val title: String,
    val description: String,
    val url: String,
    val publishedDate: String,
    val publisher: String,
    val assetTicker: String,
    val summary: String,
)
