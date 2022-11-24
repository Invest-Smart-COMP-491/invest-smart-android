package com.comp491.investsmart.domain.news.repositories

import com.comp491.investsmart.domain.news.entities.News

interface NewsRepository {

    suspend fun getAllNews(): List<News>
    suspend fun getAssetNews(assetTicker: String): List<News>
}
