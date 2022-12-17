package com.comp491.investsmart.domain.news.repositories

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.news.entities.News

interface NewsRepository {

    suspend fun getAllNews(): Result<List<News>>
    suspend fun getAssetNews(assetTicker: String): Result<List<News>>
}
