package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.domain.comments.entities.AddComment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.AddCommentUseCase
import javax.inject.Inject

class AddCommentUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): AddCommentUseCase {

    override suspend fun invoke(assetTicker: String, text: String, parentId: Int?) {
        commentsRepository.addComment(
            addComment = AddComment(
                assetTicker = assetTicker,
                text = text,
                parentId = parentId,
            )
        )
    }
}
