package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.GetCommentByIdUseCase
import javax.inject.Inject

class GetCommentByIdUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): GetCommentByIdUseCase {

    override suspend fun invoke(commentId: Int): Result<Comment> {
        return commentsRepository.getCommentById(commentId = commentId)
    }
}
