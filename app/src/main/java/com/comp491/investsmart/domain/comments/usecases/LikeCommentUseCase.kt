package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.LikeComment

interface LikeCommentUseCase {
    suspend operator fun invoke(
        parentId: Int?,
    ): Result<Unit>
}