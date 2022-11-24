package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.domain.comments.entities.Comment

interface GetAllAssetCommentsUseCase {

    suspend operator fun invoke(assetTicker: String): List<Comment>
}
