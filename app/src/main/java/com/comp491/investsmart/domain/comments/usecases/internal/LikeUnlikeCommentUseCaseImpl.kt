package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.CommentAction
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.LikeUnlikeCommentUseCase
import javax.inject.Inject

class LikeUnlikeCommentUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): LikeUnlikeCommentUseCase {

    override suspend fun invoke(commentId: Int, commentAction: CommentAction): Result<Unit> {
        return when (commentAction) {
            CommentAction.UNLIKE -> {
                commentsRepository.unlikeComment(commentId)
            }
            CommentAction.LIKE -> {
                commentsRepository.likeComment(commentId)
            }
        }
    }
}