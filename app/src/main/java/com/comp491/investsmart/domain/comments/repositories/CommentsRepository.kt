package com.comp491.investsmart.domain.comments.repositories

import com.comp491.investsmart.domain.comments.entities.AddComment
import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.data.api.Result

interface CommentsRepository {

    suspend fun getAssetComments(assetTicker: String?, commentParent: String?, userId: Int?): Result<List<Comment>>
    suspend fun addComment(addComment: AddComment): Result<Unit>
    suspend fun getCommentById(commentId: Int): Result<Comment>
    suspend fun likeComment(commentId: Int): Result<Unit>
    suspend fun unlikeComment(commentId: Int): Result<Unit>
    suspend fun getUserLikedComments(userId: Int?): Result<List<Comment>>
}
