package com.comp491.investsmart.domain.news.usecases

import com.comp491.investsmart.domain.news.entities.News

interface GetAssetNewsUseCase {

    suspend operator fun invoke(assetTicker: String): List<News>
}
