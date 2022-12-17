package com.comp491.investsmart.domain.comments.repositories

import com.comp491.investsmart.domain.comments.entities.AddComment
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.data.api.Result

interface CommentsRepository {

    suspend fun getAssetComments(assetTicker: String): Result<List<Comment>>
    suspend fun addComment(addComment: AddComment): Result<Unit>
}
