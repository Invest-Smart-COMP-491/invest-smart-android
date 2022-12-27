package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.data.api.Result

interface AddCommentUseCase {

    suspend operator fun invoke(
        assetTicker: String,
        text: String,
        parentId: Int?,
    ): Result<Unit>
}
