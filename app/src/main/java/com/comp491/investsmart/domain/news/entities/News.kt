package com.comp491.investsmart.domain.news.entities

import java.util.*

data class News(
    val title: String,
    val url: String,
    val publishedDate: String,
    val publisher: String,
    val assetTicker: String,
    val summary: String,
    val thumbnail: String?,
)
