package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.CommentAction

interface LikeUnlikeCommentUseCase {
    suspend operator fun invoke(commentId: Int, commentAction: CommentAction): Result<Unit>
}