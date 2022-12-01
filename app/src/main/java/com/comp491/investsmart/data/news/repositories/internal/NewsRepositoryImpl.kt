package com.comp491.investsmart.data.news.repositories.internal

import com.comp491.investsmart.domain.news.entities.News
import com.comp491.investsmart.domain.news.repositories.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
) : NewsRepository {

    override suspend fun getAllNews(): List<News> {
        TODO("Not yet implemented")
    }

    override suspend fun getAssetNews(assetTicker: String): List<News> {
        TODO("Not yet implemented")
    }
}
