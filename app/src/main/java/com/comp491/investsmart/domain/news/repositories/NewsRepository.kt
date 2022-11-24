package com.comp491.investsmart.domain.news.repositories

<<<<<<< HEAD
import com.comp491.investsmart.domain.news.entities.News

interface NewsRepository {

    suspend fun getAllNews(): List<News>
    suspend fun getAssetNews(assetTicker: String): List<News>
}
=======
interface NewsRepository {
}
>>>>>>> 54037c51dcf75ddfa340c2d2b8e5f5f4d767db94
