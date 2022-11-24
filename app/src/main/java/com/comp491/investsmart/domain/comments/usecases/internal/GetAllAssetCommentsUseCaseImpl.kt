package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.GetAllAssetCommentsUseCase
import javax.inject.Inject

class GetAllAssetCommentsUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): GetAllAssetCommentsUseCase {

    override suspend fun invoke(assetTicker: String): List<Comment> {
        return commentsRepository.getAllAssetComments(assetTicker = assetTicker)
    }
}
