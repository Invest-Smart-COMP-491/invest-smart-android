package com.comp491.investsmart.domain.comments.entities

import java.util.Date

data class Comment(
    val username: String,
    val assetTicker: String,
    val text: String,
    val date: Date,
    val likeCount: Int,
    val importedFrom: String,
)
