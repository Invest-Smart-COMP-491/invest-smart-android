package com.comp491.investsmart.domain.comments.entities

import com.comp491.investsmart.data.comments.entities.LikeCommentEntity

data class LikeComment(
    val parentId: Int?,
)

fun LikeComment.toEntity(): LikeCommentEntity {
    return LikeCommentEntity(
        parentId = parentId,
    )
}
