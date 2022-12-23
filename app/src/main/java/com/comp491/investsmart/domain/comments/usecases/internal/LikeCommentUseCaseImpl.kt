package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.LikeComment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.AddCommentUseCase
import com.comp491.investsmart.domain.comments.usecases.LikeCommentUseCase
import javax.inject.Inject

class LikeCommentUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): LikeCommentUseCase {

    override suspend fun invoke(parentId: Int?): Result<Unit> {
        return commentsRepository.likeComment(
            likeComment = LikeComment(
                parentId = parentId,
            )
        )
    }
}
