package com.comp491.investsmart.domain.comments.entities

import com.comp491.investsmart.data.comments.entities.AddCommentEntity

data class AddComment(
    val assetTicker: String,
    val text: String,
    val parentId: Int?,
)

fun AddComment.toEntity(): AddCommentEntity {
    return AddCommentEntity(
        assetTicker = assetTicker,
        text = text,
        parentId = parentId,
    )
}
