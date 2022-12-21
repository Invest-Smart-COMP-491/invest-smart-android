package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.domain.comments.entities.Comment
import com.comp491.investsmart.data.api.Result

interface GetAssetCommentsUseCase {

    suspend operator fun invoke(assetTicker: String, commentParent: String): Result<List<Comment>>
}
