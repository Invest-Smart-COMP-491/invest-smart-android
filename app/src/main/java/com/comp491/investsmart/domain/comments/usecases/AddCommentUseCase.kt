package com.comp491.investsmart.domain.comments.usecases

interface AddCommentUseCase {

    suspend operator fun invoke(
        assetTicker: String,
        text: String,
        parentId: Int?,
    )
}