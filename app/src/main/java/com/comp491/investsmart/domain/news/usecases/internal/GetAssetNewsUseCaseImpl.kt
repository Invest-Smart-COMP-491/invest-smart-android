package com.comp491.investsmart.domain.news.usecases.internal

import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.repositories.NewsRepository
import com.comp491.investsmart.domain.news.usecases.GetAssetNewsUseCase
import javax.inject.Inject

class GetAssetNewsUseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository,
): GetAssetNewsUseCase {

    override suspend fun invoke(assetTicker: String): List<News> {
        return newsRepository.getAssetNews(assetTicker = assetTicker)
    }
}
