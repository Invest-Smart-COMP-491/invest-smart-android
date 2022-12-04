package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import javax.inject.Inject

class GetAssetCommentsUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): GetAssetCommentsUseCase {

    override suspend fun invoke(assetTicker: String): List<Comment> {
        return commentsRepository.getAssetComments(assetTicker = assetTicker)
    }
}
