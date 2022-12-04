package com.comp491.investsmart.data.comments.repositories.internal

import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(

): CommentsRepository {

    override suspend fun getAssetComments(assetTicker: String): List<Comment> {
        TODO("Not yet implemented")
    }
}
