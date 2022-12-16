package com.comp491.investsmart.domain.news.usecases

import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.data.api.Result

interface GetAllNewsUseCase {

    suspend operator fun invoke(): Result<List<News>>
}
