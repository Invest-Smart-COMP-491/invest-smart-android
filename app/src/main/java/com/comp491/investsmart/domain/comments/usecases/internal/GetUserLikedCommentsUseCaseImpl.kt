package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.GetUserLikedCommentsUseCase
import javax.inject.Inject

class GetUserLikedCommentsUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): GetUserLikedCommentsUseCase {

    override suspend fun invoke(userId: Int?): Result<List<Comment>> {
        return commentsRepository.getUserLikedComments(userId = userId)
    }
}