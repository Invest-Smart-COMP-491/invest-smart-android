package com.comp491.investsmart.data.comments.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class AddCommentEntity (
    @Json(name = "asset_ticker")
    val assetTicker: String,

    @Json(name = "comment_text")
    val text: String,

    @Json(name = "parent_comment_id")
    val parentId: Int?,
)
