package com.comp491.investsmart.data.comments.entities

import androidx.annotation.Keep
import com.comp491.investsmart.domain.comments.entities.Comment
import com.squareup.moshi.Json

@Keep
data class CommentEntity(
    @Json(name = "username")
    val username: String,

    @Json(name = "user")
    val userId: Int,

    @Json(name = "asset_ticker")
    val assetTicker: String,

    @Json(name = "comment_text")
    val text: String,

    @Json(name = "date_time")
    val date: String,

    @Json(name = "liked_users")
    val likedUsers: List<Int>,

    @Json(name = "imported_from")
    val importedFrom: String?,
)

fun CommentEntity.toDomain(): Comment {
    return Comment(
        id = 0,
        username = username,
        userId = userId,
        assetTicker = assetTicker,
        text = text,
        date = date,
        likeCount = likedUsers.size,
        importedFrom = importedFrom,
        answerCount = 0,
    )
}
