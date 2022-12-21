package com.comp491.investsmart.domain.comments.usecases.internal

import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import com.comp491.investsmart.domain.comments.usecases.GetAssetCommentsUseCase
import javax.inject.Inject
import com.comp491.investsmart.data.api.Result

class GetAssetCommentsUseCaseImpl @Inject constructor(
    private val commentsRepository: CommentsRepository,
): GetAssetCommentsUseCase {

    override suspend fun invoke(assetTicker: String, commentParent: String): Result<List<Comment>> {
        return commentsRepository.getAssetComments(assetTicker = assetTicker, commentParent = commentParent)
    }
}
