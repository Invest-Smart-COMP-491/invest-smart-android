package com.comp491.investsmart.data.comments.repositories.internal

import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.domain.comments.entities.AddComment
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val investSmartService: InvestSmartService,
): CommentsRepository {

    override suspend fun getAssetComments(assetTicker: String): List<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun addComment(addComment: AddComment) {
        TODO("Not yet implemented")
    }
}
