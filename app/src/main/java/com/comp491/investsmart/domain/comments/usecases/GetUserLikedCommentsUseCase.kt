package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.Comment

interface GetUserLikedCommentsUseCase {
    suspend operator fun invoke(userId: Int?): Result<List<Comment>>
}