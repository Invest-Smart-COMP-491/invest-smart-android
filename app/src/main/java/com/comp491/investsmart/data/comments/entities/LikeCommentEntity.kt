package com.comp491.investsmart.data.comments.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class LikeCommentEntity (
    @Json(name = "parent_comment_id")
    val parentId: Int?,
)
