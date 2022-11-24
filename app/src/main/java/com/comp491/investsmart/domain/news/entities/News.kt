package com.comp491.investsmart.domain.news.entities

import java.util.*

data class News(
    val title: String,
    val description: String,
    val url: String,
    val publishedDate: Date,
    val publisher: String,
    val assetTicker: String,
)
