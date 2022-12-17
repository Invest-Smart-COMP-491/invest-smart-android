package com.comp491.investsmart.domain.news.usecases

import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.data.api.Result

interface GetAssetNewsUseCase {

    suspend operator fun invoke(assetTicker: String): Result<List<News>>
}
