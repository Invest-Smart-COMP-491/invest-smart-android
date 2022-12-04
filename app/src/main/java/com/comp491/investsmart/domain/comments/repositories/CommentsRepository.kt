package com.comp491.investsmart.domain.comments.repositories

import com.comp491.investsmart.domain.comments.entities.Comment

interface CommentsRepository {

    suspend fun getAssetComments(assetTicker: String): List<Comment>
}
