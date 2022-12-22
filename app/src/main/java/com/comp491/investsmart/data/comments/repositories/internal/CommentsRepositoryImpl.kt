package com.comp491.investsmart.data.comments.repositories.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.data.api.safeApiCall
import com.comp491.investsmart.data.comments.entities.toDomain
import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.comments.entities.AddComment
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.domain.comments.entities.toEntity
import com.comp491.investsmart.domain.comments.repositories.CommentsRepository
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val investSmartService: InvestSmartService,
): CommentsRepository {

    override suspend fun getAssetComments(assetTicker: String?, commentParent: String?, userId: Int?): Result<List<Comment>> {
        val result = safeApiCall {
            investSmartService.getAssetComments(assetTicker = assetTicker, commentParent = commentParent, userId = userId)
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.map { it.toDomain() }  ?: emptyList())
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun addComment(addComment: AddComment): Result<Unit> {
        return safeApiCall {
            investSmartService.addComment(
                token = dataStoreManager.getLatestToken(),
                addCommentEntity = addComment.toEntity(),
            )
        }
    }

    override suspend fun getCommentById(commentId: Int): Result<Comment> {
        val result = safeApiCall {
            investSmartService.getCommentById(comment_id = commentId)
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.toDomain()  ?: Comment(0, "", 0, "", "", "", 0, "", 0))
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }
}
