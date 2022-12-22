package com.comp491.investsmart.domain.comments.usecases

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.comments.entities.Comment

interface GetCommentByIdUseCase {
    suspend operator fun invoke(commentId: Int): Result<Comment>
}