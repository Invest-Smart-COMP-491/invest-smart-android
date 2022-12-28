package com.comp491.investsmart.domain.comments.entities

data class Comment(
    val id: Int,
    val username: String,
    val userId: Int,
    val assetTicker: String,
    val text: String,
    val date: String,
    val likeCount: Int,
    val importedFrom: String?,
    val answerCount: Int,
)
